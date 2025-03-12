package org.mik.first.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.mik.first.domain.Client;
import org.mik.first.domain.Job;
import org.mik.first.domain.JobType;
import org.mik.first.repository.ClientRepository;
import org.mik.first.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class JobService extends AbstractService<Long, Job>{
    private static final boolean TEST=false;

    private final JobRepository jobRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public JobService(JobRepository jobRepository,
                      ClientRepository clientRepository) {
        super();
        this.jobRepository=jobRepository;
        this.clientRepository=clientRepository;
    }

    @PostConstruct
    public void init() {
        if (!TEST)
            return;
    }

    @Transactional
    public synchronized void addJob(Client seller, Client buyer, String name, int value) {
        if (seller==null || buyer==null || name==null || name.isBlank()) {
            log.warn("seller or buyer or name is null");
            return;
        }

        if (buyer.getAmount()<value) {
            log.warn("buyer %s does not have enough money to buy %s (%d)".formatted(buyer, name, value));
            return;
        }

        Job jobSeller=Job.builder()
                .jobName(name)
                .jobType(JobType.SELL)
                .value(value)
                .client(seller)
                .build();
        seller.setAmount(seller.getAmount()+value);
        Job jobBuyer=Job.builder()
                .jobName(name)
                .jobType(JobType.BUY)
                .value(value)
                .client(buyer)
                .build();
        buyer.setAmount(buyer.getAmount()-value);
        clientRepository.save(seller);
        clientRepository.save(buyer);
        jobRepository.save(jobSeller);
        jobRepository.save(jobBuyer);
    }
}
