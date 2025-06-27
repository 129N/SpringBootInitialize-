package org.mik.first.rest;

import org.mik.first.domain.Country;
import org.mik.first.testdata.CountryData;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class CountryRestTest extends AbstractRestTest<Long, Country, Exception>{
    public CountryRestTest(TestRestTemplate restTemplate, String url, JpaRepository<Country, Long> repository) {
        super(restTemplate, url, repository);
    }
    @Override
    protected ParameterizedTypeReference<List<Country>>
    getParameterizedTypeReference(){
        return new ParameterizedTypeReference<>() {};
    }

    @Override
    protected Class<Country> getClazz(){
        return Country.class;
    }

    @Override
    protected List<ValidEntity<Long, Country>> getValidEntities() {
        return CountryData.TEST_DATA.stream()
                .map(c -> new ValidEntity<>(c, e -> {
                    e.setName(e.getName() + "_m");
                    e.setSign("aa");
                })).toList();  // Make sure this exists in CountryData
    }

    @Override
    protected List<InvalidEntry<Long, Country, Exception>> getInvalildEntity(){
        return CountryData.INVALID_DATA;
    }


    @Override
    protected List<Country> getTestData(){
        return CountryData.TEST_DATA;
    }



}
