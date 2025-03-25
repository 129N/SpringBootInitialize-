package org.mik.first.entity;

import org.mik.first.domain.Company;
import org.mik.first.repository.CompanyRepository;
import org.mik.first.testdata.CompanyData;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class CompanyEntityTest extends AbstractEntityTest<Long, Company, Exception> implements CompanyData {

    public CompanyEntityTest(CompanyRepository repository) {
        super(repository);
    }

    @Override
    protected Class<Company> getClazz() {
        return Company.class;
    }

    @Override
    protected List<InvalidEntry<Long, Company, Exception>> getInvalidEntities() {
        return CompanyEntityTest.INVALID_DATA;
    }

    @Override
    protected List<ValidEntity<Long, Company>> getValidEntities() {
        return TEST_DATA.stream()
                .map(c->new ValidEntity<>(c,
                        e->{
                            e.setCapitalisation(e.getCapitalisation()+1);
                            e.setEstablished(e.getEstablished()+1);
                        }))
                .toList();
    }

    @Override
    protected ParameterizedTypeReference<List<Company>> getParametrizedTypeReference() {
        return new ParameterizedTypeReference<>() {};
    }

    @Override
    protected List<Company> getTestData() {
        return TEST_DATA;
    }

    @Override
    protected void afterTest() {
        saveToDatabase();
    }
}
