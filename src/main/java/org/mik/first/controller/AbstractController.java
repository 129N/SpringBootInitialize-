package org.mik.first.controller;

import com.sun.jdi.event.ExceptionEvent;
import jakarta.validation.Valid;
import org.mik.first.domain.AbstractDomain;
import org.mik.first.exception.ResourceNotFoundException;
import org.mik.first.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.io.Serializable;

public abstract class AbstractController <ID extends Serializable, E extends AbstractDomain<ID>>{

    protected AbstractService<ID, E> service;

    public AbstractController ( AbstractService<ID,E> service){
        this.service = service;

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<E> create(@Valid @RequestBody E entity) throws Exception{
        return ResponseEntity.ok(this.service.save(entity));
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<E> update(@PathVariable("id") ID id, @Valid @RequestBody E entity) throws Exception {
        return ResponseEntity.ok(this.service.save(id, entity));
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<E> find(@PathVariable ID id) throws Exception{
        return ResponseEntity.ok(this.service.findByID(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cannot find entity with i:d%s".formatted(id)) ));
    }


    @DeleteMapping
    public ResponseEntity<Void> delete(E e) throws Exception {
        this.service.delete(e);
        return ResponseEntity.ok().build();
    }




}
