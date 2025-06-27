package org.mik.first.testdata;

import org.mik.first.AbstractTest;
import org.mik.first.Const;
import org.mik.first.domain.Company;

import java.util.LinkedList;
import java.util.List;

public interface CompanyData extends Data {
    List<Company> TEST_DATA = new LinkedList<>(
            List.of(
                    Company.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Sirius"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
                            .established(1978)
                            .taxId("123456789")
                            .capitalisation(84_000L)
                            .build(),
                    Company.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Infinite"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
                            .established(500)
                            .taxId("234567890")
                            .capitalisation(60_000L)
                            .build(),
                    Company.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("The Hitchhiker"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
                            .established(1980)
                            .taxId("345678901")
                            .capitalisation(100_000L)
                            .build(),
                    Company.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Deep"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
                            .established(1000)
                            .taxId("456789012")
                            .capitalisation(90_000L)
                            .build(),
                    Company.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("The Restaurant"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
                            .established(1500)
                            .taxId("567890123")
                            .capitalisation(41_000L)
                            .build(),
                    Company.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("The Vogon"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
                            .established(300)
                            .taxId("678901234")
                            .capitalisation(10_000L)
                            .build(),
                    Company.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Magrathea"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
                            .established(50)
                            .taxId("789012345")
                            .capitalisation(42_000L)
                            .build()
            )
    );

    List<AbstractTest.InvalidEntry<Long, Company, Exception>> INVALID_DATA = List.of(
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Company.builder()
//                                .client(ClientData.TEST_DATA.stream().filter(c->c.getName().startsWith("Sirius"))
//                                        .findFirst()
//                                        .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
                                    .established(1978)
                                    .taxId("123456789")
                                    .capitalisation(84000L)
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Company.builder()
                                    .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Sirius"))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
//                                .established(1978)
                                    .taxId("123456789")
                                    .capitalisation(84000L)
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Company.builder()
                                    .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Sirius"))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
                                    .established(1978)
//                                .taxId("123456789")
                                    .capitalisation(84000L)
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Company.builder()
                                    .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Sirius"))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
                                    .established(1978)
                                    .taxId("123456789")
                                    .capitalisation(84000L)
                                    .build(),
                            Company.builder()
                                    .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Sirius"))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND)))
                                    .established(1978)
                                    .taxId("123456789")
                                    .capitalisation(84000L)
                                    .build()
                    ),
                    Exception.class
            )
    );

}
