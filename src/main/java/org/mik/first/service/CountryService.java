package org.mik.first.service;

import org.mik.first.controller.CountryController;
import org.mik.first.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends  AbstractService<Long, Country> {


    public CountryService(JpaRepository<Country, Long> repository){
        super(repository);
    }

    @Override
    protected Country copy(Country original, Country modified){
        return null;
    }

}
