package org.mik.first;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.mik.first.domain.AbstractDomain;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractTest<ID extends Serializable, E extends AbstractDomain<ID>, X extends Exception> {

    @AllArgsConstructor
    @Data
    public  static class ValidEntity<ID extends Serializable, E extends AbstractDomain<ID>, X extends Exception > {
        public E entity;
        public Consumer<E> onUpdate;
    }

    @AllArgsConstructor
    @Data
    public static class InvalidEntity<ID extends Serializable, E extends AbstractDomain<ID>, X extends Exception> {
        List<E> entity;
        Class<X> Exception;
    }

    protected List<ValidEntity<ID, E, X>> validEntities;

    protected  List<InvalidEntity<ID,E,X>> invalidEntities;

    protected  JpaRepository<E,ID> repository;

    protected abstract Class<E> getClazz();

    protected abstract List<ValidEntity<ID, E, X>> getValidEntities();
    protected abstract List<InvalidEntity<ID, E, X>> getInvalidEntities();

    protected abstract ParameterizedTypeReference<List<E>> getParametrizedTypeReference();
    protected abstract List<E> getTestData();

    protected abstract void test();

    public AbstractTest(JpaRepository<E,ID> repository){
        this.repository = repository;
    }

    protected void beforeTest() {
        this.validEntities=getValidEntities();
        if(validEntities==null || validEntities.isEmpty()) {
            Assertions.fail("Valid entities cannot be empty ");
        }

    }


    protected void afterTest(){

    }


    public void start() {
        beforeTest();
        test();
        afterTest();
    }


    protected void saveToDatabase() {
        this.repository.deleteAll();
        List<E> data=getTestData();
        this.repository.saveAll(data);
        data.clear();
        data.addAll(this.repository.findAll());
    }


}
