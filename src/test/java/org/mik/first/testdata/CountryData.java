package org.mik.first.testdata;

import org.mik.first.AbstractTest;
import org.mik.first.domain.Country;

import java.util.LinkedList;
import java.util.List;

public interface CountryData extends Data {

    List<Country> TEST_DATA = new LinkedList<>(
            List.of(
                    Country.builder().name("USA").sign("US").build(),
                    Country.builder().name("Hungary").sign("HU").build(),
                    Country.builder().name("United Kingdom").sign("GB").build(),
                    Country.builder().name("France").sign("FR").build(),
                    Country.builder().name("Germany").sign("DE").build(),
                    Country.builder().name("Italy").sign("IT").build(),
                    Country.builder().name("Spain").sign("ES").build(),
                    Country.builder().name("China").sign("CN").build(),
                    Country.builder().name("Japan").sign("JP").build(),
                    Country.builder().name("Brazil").sign("BR").build(),
                    Country.builder().name("India").sign("IN").build(),
                    Country.builder().name("Canada").sign("CA").build(),
                    Country.builder().name("Australia").sign("AU").build(),
                    Country.builder().name("South Africa").sign("ZA").build(),
                    Country.builder().name("Betelgeuse").sign("BET").build(),
                    Country.builder().name("Earth").sign("EARTH").build(),
                    Country.builder().name("Viltvolde VI").sign("VV").build(),
                    Country.builder().name("Vogsphere").sign("VOG").build(),
                    Country.builder().name("Magrathean").sign("MA").build(),
                    Country.builder().name("Ursa Minor Beta").sign("UMB").build()
            )
    );

    List<AbstractTest.InvalidEntry<Long, Country, Exception>> INVALID_DATA = List.of(
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Country.builder()
                                    //                        .name("test")
                                    .sign("hu")
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Country.builder()
                                    .name("test")
//                            .sign("hu")
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Country.builder()
                                    .name("test")
                                    .sign("asd")
                                    .build(),
                            Country.builder()
                                    .name("test")
                                    .sign("asd")
                                    .build()
                    ),
                    Exception.class
            )
    );


}
