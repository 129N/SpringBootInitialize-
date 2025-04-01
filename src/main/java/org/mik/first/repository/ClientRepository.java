package org.mik.first.repository;

import org.mik.first.domain.Client;
import org.mik.first.domain.Job;
import org.mik.first.domain.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


    List<Job> findByClientAndJobType(Client cl, JobType jt);

    List<Job> findByclientAndJobtypeAndStartingIsGreaterthanEqual(Client cl, JobType jt, LocalDateTime LData);
}
