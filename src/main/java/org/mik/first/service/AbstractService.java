package org.mik.first.service;


import org.mik.first.domain.AbstractDomain;

import org.mik.first.dto.AbstractDTO;
import org.mik.first.dto.mapper.CountryMapper;
import org.mik.first.dto.mapper.EntityMapper;
import org.mik.first.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public abstract class AbstractService<ID extends Serializable, T extends AbstractDomain<ID>, D extends AbstractDTO<ID> > {

    protected JpaRepository<T, ID> repository;
    protected EntityMapper<ID,T,D> entityMapper;

    public AbstractService(JpaRepository<T,ID> repository, EntityMapper<ID,T, D> entityMapper) {
        this.repository=repository;
        this.entityMapper=entityMapper;

    }


    protected abstract T copy(T original, T modified);

    @Transactional
    public D save(D dto) {
        T entity = this.entityMapper.toEntity(dto);
        return this.entityMapper.toDTO(this.repository.save(entity));
    }

    @Transactional
    public D save(ID id, D d) throws ResourceNotFoundException {
        T entity = this.repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cannot find by id:%s".formatted(id)));
        this.entityMapper.updateFromDto(d, entity);
        return this.entityMapper.toDTO(this.repository.save(entity) );
    }

    public D findById(ID id) throws ResourceNotFoundException {
        return this.entityMapper.toDTO(this.repository.findById(id)
                .orElseThrow( ()-> new ResourceNotFoundException("Cannot find by id%s"
                .formatted(id)) ) );
    }

    public List<D> findAll() {
        return this.repository.findAll()
                .stream().map(e -> this.entityMapper.toDTO(e))
                .toList();
    }

    public Page<D> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(e->this.entityMapper.toDTO(e));
    }

    @Transactional
    public void delete(D d) throws ResourceNotFoundException{
        T entity = this.repository.findById(d.getId())
                .orElseThrow( () -> new ResourceNotFoundException("Cannot find by id%s".formatted(d.getId() ) ));
        this.repository.delete(entity);
    }




}
