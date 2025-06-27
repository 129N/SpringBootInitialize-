package org.mik.first.testdata;

import org.mik.first.AbstractTest;
import org.mik.first.domain.Client;
import org.mik.first.domain.Country;

import java.util.LinkedList;
import java.util.List;

public interface ClientData extends Data{

    public static List<Client> TEST_DATA = new LinkedList<>(
            List.of(
                    Client.builder().name("Zaphod Beeblebrox").address("Betelgeuse City").amount(42)
                            .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("BET"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder().name("Ford Prefect").address("Betelgeuse City").amount(142)
                            .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("BET"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder().name("Arthur Dent").address("London").amount(10042)
                            .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("EARTH"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder().name("Tricia McMillan").address("Leshoto").amount(12)
                            .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("ZA"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder().name("Humma Kavula").address("Viltvolde VI").amount(20042)
                            .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("VV"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder().name("Prostenic Vogon Jeltz").address("Vogaria").amount(70042)
                            .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("VOG"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder().name("Slartibartfast").address("Magrathea").amount(71)
                            .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("VOG"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder().name("Marvin the Paranoid Android").address("Betelgeuse").amount(1)
                            .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("BET"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("Sirius Cybernetics Corporation")
                            .address("Ursa Minor Beta")
                            .amount(1000)
                            .country(CountryData.TEST_DATA.stream().filter(c->c.getSign().equals("UMB"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("Infinite Improbability Drive")
                            .address("Ursa Minor Beta")
                            .amount(2000)
                            .country(CountryData.TEST_DATA.stream().filter(c->c.getSign().equals("UMB"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("The Hitchhiker's Guide to the Galaxy")
                            .address("London")
                            .amount(5000)
                            .country(CountryData.TEST_DATA.stream()
                                    .filter(c->c.getSign().equals("EARTH"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("Deep Thought")
                            .address("New York")
                            .amount(300)
                            .country(CountryData.TEST_DATA.stream().filter(c->c.getSign().equals("EARTH"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("The Restaurant at the End of the Universe")
                            .address("Viltvolde VI")
                            .amount(10)
                            .country(CountryData.TEST_DATA.stream().filter(c->c.getSign().equals("VV"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("The Vogon Constructor Fleet")
                            .address("Vogsphere")
                            .amount(10000)
                            .country(CountryData.TEST_DATA.stream().filter(c->c.getSign().equals("VOG"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("Magrathea planet builder Ltd")
                            .address("Magrathea")
                            .amount(12000)
                            .country(CountryData.TEST_DATA.stream().filter(c->c.getSign().equals("MA"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                            .build()
            )
    );


    public static List<AbstractTest.InvalidEntry<Long, Client, Exception>> INVALID_DATA = List.of(
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Client.builder()
//                                .name("test")
                                    .address("test")
                                    .amount(0)
                                    .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("BET"))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Client.builder()
                                    .name("test")
//                                .address("test")
                                    .amount(0)
                                    .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("BET"))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Client.builder()
                                    .name("test")
                                    .address("test")
//                                .amount(0)
                                    .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("BET"))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Client.builder()
                                    .name("test")
                                    .address("test")
                                    .amount(0)
//                                .country(CountryData.TEST_DATA.stream().filter(c -> c.getSign().equals("BET"))
//                                        .findFirst()
//                                        .orElseThrow(() -> new RuntimeException(Country.COUNTRY_NOT_FOUND)))
                                    .build()
                    ),
                    Exception.class
            )
    );

}
