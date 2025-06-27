package org.mik.first.dto.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mik.first.domain.AbstractDomain;
import org.mik.first.domain.Client;
import org.mik.first.dto.AbstractDTO;
import org.mik.first.dto.ClientDTO;

import java.io.Serializable;

public interface EntityMapper <ID extends Serializable, E extends AbstractDomain<ID>, D extends AbstractDTO>{
    @Mapping(source = "country.Id", target = "CountryID")
    ClientDTO toDto(Client entitiy);

    E toEntity(D dto);
    D toDTO(E entity);

    void updateFromDto(D dto, @MappingTarget E entity);
}
