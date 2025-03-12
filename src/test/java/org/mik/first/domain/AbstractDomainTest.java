package org.mik.first.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

@Log4j2
public abstract class AbstractDomainTest<ID extends Serializable, E extends AbstractDomain<ID>> {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ConstraintViolationEntry<ID extends Serializable, E extends AbstractDomain<ID>> {
        E entity;
        Class<Exception> expectedException;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ValidEntity<ID extends Serializable, E extends AbstractDomain<ID>> {
        E entity;
        Consumer<E> onUpdate;
    }

    protected PostgreSQLContainer<?> postgres;
    protected JpaRepository<E, ID> repository;
    protected List<ConstraintViolationEntry<ID,E>> invalidEntities;
    protected List<ValidEntity<ID,E>> validEntities;

    protected abstract Class<E> getClazz();

    protected abstract List<ConstraintViolationEntry<ID,E>> getConstraintVliolationsEntities();

    protected abstract List<ValidEntity<ID,E>> getValidEntities();

    public AbstractDomainTest(PostgreSQLContainer<?> postgres, JpaRepository<E,ID> repository) {
        this.postgres=postgres;
        this.repository=repository;
    }

    public void beforeAll() {
        this.validEntities=getValidEntities();
        if (this.validEntities==null)
            throw new IllegalArgumentException("valid entities cannot be null");
        this.invalidEntities=getConstraintVliolationsEntities();
    }

    protected void beforeEntityTest() {}

    protected void afterEntityTest() {}

    protected void execInvalidTest() {
        this.invalidEntities.forEach(i->
            Assertions.assertThrows(i.expectedException, ()-> repository.save(i.entity),
                    "constraint violation, but no exception")
        );
    }

    protected void validTests() {
        this.validEntities.forEach(e-> {
            E saved = repository.save(e.entity);
            Assertions.assertNotNull(saved);
            E reloaded = this.repository.findById(saved.getId())
                    .orElseThrow(RuntimeException::new);
            Assertions.assertNotNull(reloaded);
            Assertions.assertEquals(saved,reloaded);
            e.onUpdate.accept(saved);
            E updated = this.repository.save(saved);
            Assertions.assertNotNull(updated);

            this.repository.delete(updated);
            this.repository.findById(saved.getId())
                    .ifPresent(f-> {
                        throw new RuntimeException("%s is not deleted".formatted(saved));
                    });
        });
    }

    public void entityTests() {
        beforeAll();
        beforeEntityTest();
        execInvalidTest();
        validTests();
        this.repository.deleteAll();
        this.validEntities.forEach(e->e.getEntity().setId(null));
        afterEntityTest();
    }
}
