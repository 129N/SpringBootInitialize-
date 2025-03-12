package org.mik.first.repository;

import org.hibernate.annotations.processing.SQL;
import org.mik.first.domain.Client;
import org.mik.first.domain.Job;
import org.mik.first.domain.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

//    @Query(name = "Q", nativeQuery = true, value = """
//    select J from job J where client =:cl and job_type=:jt
//    """)
//    List<Job> getClAJobType(Client cl, JobType jt);

    List<Job> findByClientAndJobType(Client cl, JobType jt);

    List<Job> findByClientAndJobTypeAndStartingIsGreaterThanEqual(Client cl, JobType jt, LocalDateTime st);

}
