package org.mik.first.entity;

import org.mik.first.domain.Person;
import org.mik.first.repository.PersonRepository;
import org.mik.first.testdata.PersonData;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class PersonEntityTest extends AbstractEntityTest<Long, Person, Exception> implements PersonData {


    public PersonEntityTest(PersonRepository repository) {
        super(repository);
    }

    @Override
    protected Class<Person> getClazz() {
        return Person.class;
    }

    @Override
    protected List<InvalidEntry<Long, Person, Exception>> getInvalidEntities() {
        return PersonEntityTest.INVALID_DATA;
    }

    @Override
    protected List<ValidEntity<Long, Person>> getValidEntities() {
        return TEST_DATA.stream().map(p -> new ValidEntity<>(p, e -> {
            e.setBirthDate(e.getBirthDate().plusYears(1));
        })).toList();
    }


    @Override
    protected List<Person> getTestData() {
        return TEST_DATA;
    }

    @Override
    protected void afterTest() {
        saveToDatabase();
    }
}
