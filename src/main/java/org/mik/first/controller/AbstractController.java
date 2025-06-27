package org.mik.first.controller;


import jakarta.validation.Valid;
import org.mik.first.domain.AbstractDomain;
import org.mik.first.dto.AbstractDTO;
import org.mik.first.exception.ResourceNotFoundException;
import org.mik.first.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;
import java.util.List;

public abstract class AbstractController<ID extends Serializable, E extends AbstractDomain<ID>,D extends AbstractDTO<ID>> {

    protected AbstractService<ID, E, D> service;

    public AbstractController(AbstractService<ID, E, D> service) {
        this.service=service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<D> create(@Valid @RequestBody D entity) throws Exception {
        return ResponseEntity.ok(this.service.save(entity));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> update(@PathVariable("id") ID id, @Valid @RequestBody D entity) throws Exception {
        return ResponseEntity.ok(this.service.save(id, entity));
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<D>> findAll() throws Exception {
        return ResponseEntity.ok(this.service.findAll());
    }
// public ResponseEntity<D> findAll(@PathVariable("id") ID id) throws Exception {
//        return ResponseEntity.ok(this.service.findById(id));
//    }

    @DeleteMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(D e) throws Exception {
        this.service.delete(e);
        return ResponseEntity.ok().build();
    }

}
