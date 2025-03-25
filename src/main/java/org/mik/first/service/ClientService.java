package org.mik.first.service;

import lombok.extern.log4j.Log4j2;
import org.mik.first.domain.Client;
import org.mik.first.domain.Job;
import org.mik.first.domain.JobType;
import org.mik.first.repository.ClientRepository;
import org.mik.first.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Log4j2
@Service
@Transactional
public class ClientService extends AbstractService<Long, Client> {

    private final JobRepository jobRepository;

    @Autowired
    public ClientService(ClientRepository repository,
                         JobRepository jobRepository) {
        super(repository);
        this.jobRepository = jobRepository;
    }


    @Transactional
    public void addJob(Client seller,  Client buyer, String name, int value) {
        if (!(seller!=null&&name!=null&&buyer!=null))
            throw new IllegalArgumentException("Seller, buyer and job name must not be null");

        if (seller.getAmount()<value)
            throw new IllegalArgumentException("Seller has not enough money: %s".formatted(seller));

        Job sellerJob = Job.builder()
                .name(name)
                .value(value)
                .client(seller)
                .jobType(JobType.SELL)
                .starting(LocalDateTime.now())
                .build();

        if (seller.getJobs() != null && seller.getJobs().contains(sellerJob))
            throw new IllegalArgumentException("Job already exists in seller: %s".formatted(seller));

        Job buyerJob = Job.builder()
                .name(name)
                .value(value)
                .client(buyer)
                .jobType(JobType.BUY)
                .starting(LocalDateTime.now())
                .build();

        if (buyer.getJobs()!=null && buyer.getJobs().contains(buyerJob))
            throw new IllegalArgumentException("Job already exists in buyer: %s".formatted(buyer));

        synchronized (this) {
            seller.getJobs().add(sellerJob);
            seller.setAmount(seller.getAmount()-value);
            jobRepository.save(sellerJob);
            jobRepository.save(buyerJob);
            buyer.getJobs().add(buyerJob);
            buyer.setAmount(buyer.getAmount()+value);
            repository.save(seller);
            repository.save(buyer);
        }
    }

    public List<Job> getJobs(Client client, JobType jobType) {
        return this.jobRepository.findByClientAndJobType(client, jobType);
    }

    public List<Job> getStartedJobs(Client client, JobType jobType, LocalDateTime starting) {
        return this.jobRepository.findByClientAndJobTypeAndStartingIsGreaterThanEqual(client, jobType, starting);
    }

    @Override
    protected Client copy(Client original, Client modified) {
        original.setAmount(modified.getAmount());
        original.setName(modified.getName());
        original.setAddress(modified.getAddress());
        return original;
    }
}
