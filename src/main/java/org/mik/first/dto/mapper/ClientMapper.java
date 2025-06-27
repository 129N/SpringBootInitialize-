package org.mik.first.dto.mapper;

import org.mapstruct.*;
import org.mik.first.domain.Client;
import org.mik.first.domain.Country;
import org.mik.first.dto.ClientDTO;
import org.mik.first.exception.ResourceNotFoundException;
import org.mik.first.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public abstract class ClientMapper implements
        EntityMapper<Long, Client, ClientDTO>,
        MapperUtil<Long, Country, ClientDTO>{

    @Autowired
    private CountryRepository countryRepository;

    @Override
    @Mapping(source = "id", target ="id")
    @Mapping(source = "version", target ="version")
    @Mapping(source = "created", target ="created")
    @Mapping(source = "updated", target ="updated")
    @Mapping(source = "country.id", target = "countryID")
    public abstract   ClientDTO toDTO(Client entity);

    @Override
    @Mapping(source = "countryID", target ="country", qualifiedByName = "getCountryById")
    @Mapping(source = "id", target ="id")
    @Mapping(source = "version", target ="version")
    @Mapping(source = "created", target ="created")
    @Mapping(source = "updated", target ="updated")
    //@Mapping(source = "CountryID.Id", target = "country", qualifiedByName = "mapCountryTOCountry")
    public abstract  Client toEntity(ClientDTO dto);



    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "version", source = "version")
    @Mapping(target = "created", source = "created")
    @Mapping(target = "updated", source = "updated")
    @Mapping(source = "countryID", target = "country", qualifiedByName = "getCountryById")
    public abstract void updateFromDto(ClientDTO dto, @MappingTarget Client restored);


    @Named("getCountryById")
    public  Country getCountryByID(Long id) throws ResourceNotFoundException  {

        if (id == null) {
            return null;
        }

     return getbyID(id,countryRepository);
    }
}
