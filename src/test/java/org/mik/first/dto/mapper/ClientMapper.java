package org.mik.first.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mik.first.domain.Client;
import org.mik.first.domain.Country;
import org.mik.first.dto.ClientDTO;


@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<Long, Client, ClientDTO>{

    @Override
    @Mapping(source = "CountryID.Id", target = "country", qualifiedByName = "mapCountryTOCountry")
    Client toEntity(ClientDTO dto);

    @Named("mapCountryTOCountry")
    default Country mapCountryIDTOCountry(Long id) {
        if (id == null) {
            return null;
        }

        Country country = new Country();
        country.setId(id);
        return country;
    }
}
