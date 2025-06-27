package org.mik.first.dto.mapper;

import org.mapstruct.*;
import org.mik.first.domain.Client;
import org.mik.first.domain.Person;
import org.mik.first.dto.ClientDTO;
import org.mik.first.dto.PersonDTO;
import org.mik.first.exception.ResourceNotFoundException;
import org.mik.first.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring",
        uses = {ClientMapper.class, CountryMapper.class},
        injectionStrategy = InjectionStrategy.FIELD)

public abstract class PersonMapper implements EntityMapper<Long, Person, PersonDTO>,
        MapperUtil<Long, Client, ClientDTO>{

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Mapping(source = "client", target = "client", qualifiedByName = "getAndUpdateClient")
    public abstract Person toEntity(PersonDTO dto);

    @Override
    @Mapping(source = "client", target = "client")
    public abstract PersonDTO toDTO(Person entity);

    @Override
    @Mapping(source = "client", target = "client", qualifiedByName = "getAndUpdateClient")
    public abstract void updateFromDto(PersonDTO dto, @MappingTarget Person restored);

    @Named("getAndUpdateClient")
    public Client getAndUpdateClient(ClientDTO dto) throws ResourceNotFoundException {
        return toEntityWithUpdate(dto, clientRepository, clientMapper, true);
    }
}
