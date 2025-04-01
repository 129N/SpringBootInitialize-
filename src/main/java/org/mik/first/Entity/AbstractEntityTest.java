package org.mik.first.Entity;

import lombok.extern.log4j.Log4j2;
import org.mik.first.AbstractTest_main;
import org.mik.first.domain.AbstractDomain;

import java.io.Serializable;


@Log4j2
public abstract class AbstractEntityTest<ID extends Serializable, E extends AbstractDomain<ID>, X extends Exception > {

    extends AbstractTest_main<ID,E, X>

    {

        public AbstractEntityTest(JpaRepository<E,ID> repository){
            super(repository);
            this.repository = repository;
        }

    }
}
