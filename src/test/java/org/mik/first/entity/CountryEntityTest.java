package org.mik.first.entity;

import org.mik.first.domain.Country;
import org.mik.first.testdata.CountryData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class CountryEntityTest extends AbstractEntityTest<Long, Country, Exception> implements CountryData {


    public CountryEntityTest(JpaRepository<Country, Long> repository) {
        super(repository);
    }

    @Override
    protected Class<Country> getClazz() {
        return Country.class;
    }

    @Override
    protected List<InvalidEntry<Long, Country, Exception>> getInvalidEntities() {
        return CountryEntityTest.INVALID_DATA;
    }

    @Override
    protected ParameterizedTypeReference<List<Country>> getParametrizedTypeReference() {
        return new ParameterizedTypeReference<>() {};
    }

    @Override
    protected List<Country> getTestData() {
        return TEST_DATA;
    }

    @Override
    protected void afterTest() {
        saveToDatabase();
    }

    @Override
    protected List<ValidEntity<Long, Country>> getValidEntities() {
        return TEST_DATA.stream()
                .map(c-> new ValidEntity<>(c, e-> {
                    e.setName(e.getName()+"_m");
                    e.setSign("asd");
                }))
                .toList();
    }

}
