package org.mik.first.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Test;
import org.mik.first.domain.Client;
import org.mik.first.domain.Job;
import org.mik.first.domain.JobType;
import org.mik.first.repository.ClientRepository;
import org.mik.first.repository.JobRepositorty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.mik.first.domain.AbstractDomain;

import jakarta.persistence.*;
//<Long, Job>

/*
@Service
public class JobService extends AbstractService<Long,Job, Jo>{
    private static final boolean TEST = false;
    private static final Logger log = LoggerFactory.getLogger(JobService.class);


    private final JobRepositorty jobRepositorty;
    private final ClientRepository clientRepository;

    @Autowired
    public JobService(JobRepositorty jobRepositorty,
                      ClientRepository clientRepository){
        super();
        this.jobRepositorty=jobRepositorty;
        this.clientRepository=clientRepository;

    }

    @PostConstruct
    public void init(){
        if(!TEST)
            return;
    }


    @Transactional
    public void addJob(Client seller, Client buyer, String name, int value){

        if(seller==null || buyer==null|| name == null ||name.isBlank()){
          log.warn("Seller or buyer or name is null");
            return;
        }

        if(buyer.getAmount()<value){
            log.warn("buyer %s does not have enough money to buy %s (%d)".formatted(buyer, name, value));
            return;
        }

        Job job_seller =Job.builder()
                .jobName(name)
                .jobType(JobType.SELL)
                .value(value)
                .client(seller)
                .build();

        seller.setAmount(seller.getAmount()+value);


        Job job_buyer =Job.builder()
                .jobName(name)
                .jobType(JobType.BUY)
                .value(value)
                .client(buyer)
                .build();

        buyer.setAmount(buyer.getAmount() - value);

        clientRepository.save(seller);
        clientRepository.save(buyer);

        jobRepositorty.save(job_seller);
        jobRepositorty.save(job_buyer);



    }
}
*/