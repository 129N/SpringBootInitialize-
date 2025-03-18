package org.mik.first.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.LinkedList;
import java.util.List;

public class CountryTest extends AbstractDomainTest<Long, Country> {

    public static List<Country> TEST_DATA = new LinkedList<>(
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

    public CountryTest(PostgreSQLContainer<?> postgres, JpaRepository<Country, Long> repository) {
        super(postgres, repository);
    }

    @Override
    protected Class<Country> getClazz() {
        return Country.class;
    }

    @Override
    protected List<ConstraintViolationEntry<Long, Country>> getConstraintVliolationsEntities() {
        return List.of(
                new ConstraintViolationEntry<>(
                        Country.builder()
//                                .name("Test country")
                                .sign("ASD")
                                .build(),
                        Exception.class
                ),
                new ConstraintViolationEntry<>(
                        Country.builder()
                                .name("Test country")
  //                              .sign("ASD")
                                .build(),
                        Exception.class
                )
        );
    }

    @Override
    protected List<ValidEntity<Long, Country>> getValidEntities() {
        return TEST_DATA.stream()
                .map(c-> new ValidEntity<>(c,
                e->{
                    e.setName(e.getName()+"_m");
                })
        ).toList();
    }

    @Override
    protected void afterEntityTest() {
        this.repository.saveAll(TEST_DATA);
        TEST_DATA.clear();
        TEST_DATA=this.repository.findAll();
    }
}
