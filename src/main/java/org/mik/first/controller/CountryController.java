package org.mik.first.controller;

import org.mik.first.Const;
import org.mik.first.domain.Country;
import org.mik.first.dto.CountryDTO;
import org.mik.first.exception.NotImplementedException;

import org.mik.first.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = Const.REST_API+"/country")
public class CountryController extends AbstractController<Long, Country, CountryDTO> {


    @Autowired
    public CountryController(CountryService service) {
        super(service);
    }


    @Override
    public ResponseEntity<Void> delete(CountryDTO e) throws Exception{
        throw new NotImplementedException("Country cannot be deleted");
    }
}
