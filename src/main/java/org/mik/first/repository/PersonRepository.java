package org.mik.first.repository;

import org.mik.first.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long > {


}
