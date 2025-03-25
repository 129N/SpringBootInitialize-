package org.mik.first.service;

import org.mik.first.domain.Client;
import org.mik.first.entity.ClientEntityTest;

import java.util.List;
import java.util.stream.IntStream;

public class ServiceTest {

    private ClientService clientService;

    public ServiceTest(ClientService clientService) {
        this.clientService = clientService;
    }

    public void testAddJob() {
        List<Client> creditworties= ClientEntityTest.TEST_DATA.stream()
                .filter(c->c.getAmount()>100)
                .toList();
        IntStream.range(0, creditworties.size()/2)
                .forEach(i->clientService.addJob(creditworties.get(i),
                         creditworties.get((creditworties.size()/2+i)), "Job %d".formatted(i), 10));
    }
}
