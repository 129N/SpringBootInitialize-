package org.mik.first.testdata;

import org.mik.first.AbstractTest;
import org.mik.first.Const;
import org.mik.first.domain.Person;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public interface PersonData extends Data {

    List<Person> TEST_DATA = new LinkedList<>(
            List.of(
                    Person.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Zaphod"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                            )
                            .personalId("123456789012")
                            .birthDate(LocalDate.of(1970, 1, 1))
                            .build(),
                    Person.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Ford"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                            )
                            .personalId("234567890123")
                            .birthDate(LocalDate.of(1870, 12, 1))
                            .build(),
                    Person.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Arthur"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                            )
                            .personalId("345678901234")
                            .birthDate(LocalDate.of(1980, 1, 1))
                            .build(),
                    Person.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Tricia"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                            )
                            .personalId("456789012345")
                            .birthDate(LocalDate.of(1985, 4, 5))
                            .build(),
                    Person.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Humma"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                            )
                            .personalId("567890123456")
                            .birthDate(LocalDate.of(1930, 7, 8))
                            .build(),
                    Person.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Prostenic"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                            )
                            .personalId("678901234567")
                            .birthDate(LocalDate.of(1000, 1, 1))
                            .build(),
                    Person.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Slarti"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                            )
                            .personalId("789012345678")
                            .birthDate(LocalDate.of(1, 8, 11))
                            .build(),
                    Person.builder()
                            .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Marvin"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                            )
                            .personalId("890123456789")
                            .birthDate(LocalDate.of(2005, 9, 11))
                            .build()
            )
    );

    List<AbstractTest.InvalidEntry<Long, Person, Exception>> INVALID_DATA = List.of(
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Person.builder()
//                    .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().equals("Zaphod Beeblebrox"))
//                            .findFirst()
//                            .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
//                    )
                                    .personalId("123456789012")
                                    .birthDate(LocalDate.of(1970, 1, 1))
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Person.builder()
                                    .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Zaphod"))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                                    )
                                    //                        .personalId("123456789012")
                                    .birthDate(LocalDate.of(1970, 1, 1))
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Person.builder()
                                    .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Zaphod"))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                                    )
                                    .personalId("123456789012")
//                    .birthDate(LocalDate.of(1970, 1, 1))
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Person.builder()
                                    .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Zaphod"))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                                    )
                                    .personalId("123456789012")
                                    .birthDate(LocalDate.of(1970, 1, 1))
                                    .build(),
                            Person.builder()
                                    .client(ClientData.TEST_DATA.stream().filter(c -> c.getName().startsWith("Zaphod"))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException(Const.CLIENT_NOT_FOUND))
                                    )
                                    .personalId("123456789012")
                                    .birthDate(LocalDate.of(1970, 1, 1))
                                    .build()
                    ),
                    Exception.class
            )
    );

}
