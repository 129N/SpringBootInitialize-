package org.mik.first.dto.mapper;

import org.mik.first.domain.AbstractDomain;
import org.mik.first.dto.AbstractDTO;
import org.mik.first.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface MapperUtil <ID extends Serializable, E extends AbstractDomain<ID>,
        D extends AbstractDTO <ID>>{

    default E toEntityWithUpdate (D dto,
                                  JpaRepository<E, ID> repository,
                                  EntityMapper<ID,E, D> mapper,
                                  boolean enableInsert) throws ResourceNotFoundException {
        if(dto==null)
            return null;

       if (dto.getId()==null) {
            if(!enableInsert)
                return null;
            E newItem = mapper.toEntity(dto);
            return repository.save(newItem);
        }

        E fromDB = repository.findById(dto.getId())
                .orElseThrow(()->new ResourceNotFoundException("Cannot find by dto:%s".formatted(dto)));

        E fromDto = mapper.toEntity(dto);
        if (fromDto.equals(fromDB))
            return fromDB;
        mapper.updateFromDto(dto, fromDB);
        return repository.save(fromDB);
    }


    default E getbyID(ID id, JpaRepository<E, ID> repository ) throws ResourceNotFoundException {
        return id == null
                 ? null
                : repository.findById(id)
                .orElseThrow( ()-> new ResourceNotFoundException("Entity not found with ID:".formatted(id) )  );
    }

}
