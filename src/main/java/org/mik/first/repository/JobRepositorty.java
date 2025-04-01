package org.mik.first.repository;

import org.mik.first.domain.Client;
import org.mik.first.domain.Job;
import org.mik.first.domain.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JobRepositorty extends JpaRepository<Job,Long> {

    //@Query(name = "Q", nativeQuery = true, value = """
    //select * from job where client_id =:cl and job_type =:jt
    //""")
    //List<Job> getClAJobType(Client cl, JobType jt);

    List<Job> findByClientAndJob(Client cl, JobType jt);

    List<Job> findByClientAndJobTYPEandStartingIsGreaterthanEqual(Client cl, JobType jt, LocalDateTime st);


}
