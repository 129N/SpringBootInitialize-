package org.mik.first.entity;

import org.mik.first.domain.Client;
import org.mik.first.repository.ClientRepository;
import org.mik.first.testdata.ClientData;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class ClientEntityTest extends AbstractEntityTest<Long, Client, Exception> implements ClientData {


    public ClientEntityTest(ClientRepository repository) {
        super(repository);
    }

    @Override
    protected Class<Client> getClazz() {
        return Client.class;
    }

    @Override
    protected List<InvalidEntry<Long, Client, Exception>> getInvalidEntities() {
        return ClientEntityTest.INVALID_DATA;
    }

    @Override
    protected List<ValidEntity<Long, Client>> getValidEntities() {
        return TEST_DATA.stream()
                .map(e -> new ValidEntity<>(e, c-> {
                            c.setName(c.getName() + "_modified");
                            c.setAddress(c.getAddress() + "_modified");
                        })
                ).toList();
    }

    @Override
    protected List<Client> getTestData() {
        return TEST_DATA;
    }

    @Override
    protected void afterTest() {
        saveToDatabase();
    }
}
