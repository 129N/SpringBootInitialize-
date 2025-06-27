package org.mik.first.repository;

import org.mik.first.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserDomainRepository extends JpaRepository<UserDomain, Long> {


    Optional<UserDomain> findByName(String name);

    @Modifying
    @Transactional
    @Query (name = "UserDomain.addrole", nativeQuery = true)
    void revokeRole(Long userId, Long roleId);


    @Modifying
    @Transactional
    @Query (name = "UserDomain.revokeROle", nativeQuery = true)
    void revokeUserROle(Long userId, Long roleId);
}
