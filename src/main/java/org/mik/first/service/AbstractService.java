package org.mik.first.service;

import org.mik.first.domain.AbstractDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<ID extends Serializable, T extends AbstractDomain<ID>> {

    protected JpaRepository<T, ID> repository;

    public AbstractService(JpaRepository<T,ID> repository) {
        this.repository=repository;
    }

    @Transactional
    public T save(T entity) {
        return this.repository.save(entity);
    }

    public Optional<T> findById(ID id) {
        return this.repository.findById(id);
    }

    public List<T> findAll() {
        return this.repository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return this.repository.findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Transactional
    public void delete(T entity) {
        this.repository.delete(entity);
    }


}
