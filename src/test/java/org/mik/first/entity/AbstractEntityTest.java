package org.mik.first.entity;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.mik.first.AbstractTest;
import org.mik.first.domain.AbstractDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

@Log4j2
public abstract class AbstractEntityTest<ID extends Serializable, E extends AbstractDomain<ID>, X extends Exception>
        extends AbstractTest<ID, E, X> {

    public AbstractEntityTest(JpaRepository<E, ID> repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void beforeTest() {
        super.beforeTest();
        this.invalidEntities = getInvalidEntities();
    }


    public void entityConstraintViolationTests() {
        this.invalidEntities.forEach(e -> {
            Assertions.assertThrows(e.getExpectedException(), () ->
                            repository.saveAll(e.getEntities()),
                    "constraints violated but no exception");
        });
    }

    protected void entityValidTests() {
        this.validEntities.forEach(e -> {
            E saved = repository.save(e.getEntity());
            Assertions.assertNotNull(saved);
            E reloaded = repository.findById(saved.getId())
                    .orElseThrow(RuntimeException::new);
            Assertions.assertNotNull(reloaded);
            Assertions.assertEquals(saved, reloaded);

            e.onUpdate.accept(reloaded);
            E updated = repository.save(reloaded);

            repository.delete(updated);
            repository.findById(updated.getId())
                    .ifPresent(d -> {
                        throw new RuntimeException("%s is not deleted".formatted(d));
                    });
        });
    }

    @Override
    protected void test() {
        entityConstraintViolationTests();
        entityValidTests();
        this.repository.deleteAll();
        this.validEntities.forEach(e -> e.getEntity().setId(null));
    }

}
