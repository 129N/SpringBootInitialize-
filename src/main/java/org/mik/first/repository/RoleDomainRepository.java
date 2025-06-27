package org.mik.first.repository;

import org.mik.first.domain.RoleDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDomainRepository extends JpaRepository<Long, RoleDomain> {

}
