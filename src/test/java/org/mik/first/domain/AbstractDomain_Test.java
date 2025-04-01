package org.mik.first.domain;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Log4j2
//public class AbstractDomain <ID extends Serializable, E extends AbstractDomain<ID>
public abstract class AbstractDomain_Test<ID extends Serializable, E extends AbstractDomain<ID>>{


    //public static class ConstrainViol<ID extends Serializable, E extends AbstractDomain<ID>>
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ConstrainViolationEntity<ID extends Serializable, E extends AbstractDomain<ID>>{
         E entity;
         Class<Exception> expectedException;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    //    public static class ValidEntity<ID extends Serializable, E extends AbstractDomain<ID> >
    public static class ValidEntity<ID extends Serializable, E extends AbstractDomain<ID>> {
        private E entity;
        private Consumer<E> onupdate;
    }

    protected PostgreSQLContainer<?> postgres;
    protected JpaRepository<E, ID> repository;


    //    protected List<ConstraintViolation<ID,E>> invalidEntities;
    protected List<ConstrainViolationEntity<ID,E>> invalidEntities;
    protected List<ValidEntity<ID,E>> validEntityList;

    protected abstract Class<E> getClazz();
    //    protected abstract List<ConstraintViolation<ID, E>> getConstraintViolationEntry;
    protected abstract List<ConstrainViolationEntity<ID,E>> getConstraintViolationEntities();
    protected abstract  List<ValidEntity<ID,E>> getValidEntities();

    public AbstractDomain_Test(PostgreSQLContainer<?> postgres, JpaRepository<E,ID> repository){
        this.postgres = postgres;
        this.repository = repository;

    }

    public void beforeAll(){
        this.validEntityList=getValidEntities();
        if(this.validEntityList ==null)
            throw new IllegalArgumentException("Valid entities cannot be null");
        this.invalidEntities = getConstraintViolationEntities();
    }

    protected void  beforeEntityTest(){}
    protected void  afterEntityTest(){}

    protected void  execInvalidTest(){
        this.invalidEntities.forEach( i-> Assertions.assertThrows(i.expectedException, ()->repository.save(i.entity),
                    "contraint violation, but no exception")
        );
    }



    protected void validTests(){
        this.validEntityList.forEach( e->{
            E saved = repository.save(e.entity);
            Assertions.assertNotNull(saved);

            E reloaded = this.repository.findById((ID) saved.getId())
                    .orElseThrow(RuntimeException:: new);

            Assertions.assertNotNull(reloaded);
            Assertions.assertEquals(saved, reloaded);

            e.onupdate.accept(saved);
            E updated = this.repository.save(saved);
            Assertions.assertNotNull(updated);

            this.repository.delete(updated);
            this.repository.findById((ID) saved.getId())
                .ifPresent(f->{throw new RuntimeException("%s is not deleted". formatted(saved));
                });

                });
    }

    public void entityTests(){
        beforeAll();
        beforeEntityTest();

        execInvalidTest();
        validTests();
        this.repository.deleteAll();
        this.validEntityList.forEach(e->e.getEntity().setId(null));
        afterEntityTest();
    }



}
