package org.mik.first.dto.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mik.first.domain.Country;
import org.mik.first.dto.CountryDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class CountryMapper implements EntityMapper<Long, Country, CountryDTO> {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "version", target = "version")
    @Mapping(source = "created", target = "created")
    @Mapping(source = "updated", target = "updated")
    public abstract Country toEntity(CountryDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "version", target = "version")
    @Mapping(source = "created", target = "created")
    @Mapping(source = "updated", target = "updated")
    @Override
    public abstract CountryDTO toDTO(Country entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "version", target = "version")
    @Mapping(source = "created", target = "created")
    @Mapping(source = "updated", target = "updated")
    @Override
    public abstract void updateFromDto(CountryDTO dto, @MappingTarget Country restored);
}
