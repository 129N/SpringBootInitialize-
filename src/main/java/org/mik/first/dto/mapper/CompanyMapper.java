package org.mik.first.dto.mapper;


import org.mapstruct.*;
import org.mik.first.domain.Client;
import org.mik.first.domain.Company;
import org.mik.first.domain.Country;
import org.mik.first.dto.ClientDTO;
import org.mik.first.dto.CompanyDTO;
import org.mik.first.exception.ResourceNotFoundException;
import org.mik.first.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel =  "spring",
uses = {ClientMapper.class, Country.class},
injectionStrategy = InjectionStrategy.FIELD)

public abstract class CompanyMapper implements EntityMapper<Long, Company, CompanyDTO>,
        MapperUtil<Long, Client, ClientDTO>{

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Mapping(source="client", target = "client", qualifiedByName = "getAndUpdateClient")
    public abstract Company toEntity(CompanyDTO dto);

    @Override
    @Mapping(source = "client", target = "client")
    public abstract CompanyDTO toDTO(Company entity);

    @Override
    @Mapping(source = "client", target = "client", qualifiedByName = "getAndUpdateClient")
    public abstract void updateFromDto(CompanyDTO dto, @MappingTarget Company restored);


    @Named("getAndUpdateClient")
    public Client getAndUpdateClient(ClientDTO dto) throws ResourceNotFoundException {
        return toEntityWithUpdate( dto, clientRepository, clientMapper, true);
    }

}
