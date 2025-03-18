package org.mik.first.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import org.mik.first.Const;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = Company.TBL_NAME)
public class Company extends AbstractDomain<Long> {
    public static final String TBL_NAME = "company";

//    public static final List<Company> COMPANIES = List.of(
//            Company.builder()
//                    .name("Sirius Cybernetics Corporation")
//                    .address("Ursa Minor Beta")
//                    .amount(1000)
//                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("UMB"))
//                            .findFirst()
//                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
//                    .taxId("123456789")
//                    .established(1978)
//                    .capitalisation(84_000L)
//                    .build(),
//            Company.builder()
//                    .name("Infinite Improbability Drive")
//                    .address("Ursa Minor Beta")
//                    .amount(2000)
//                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("UMB"))
//                            .findFirst()
//                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
//                    .taxId("987654321")
//                    .established(-1500)
//                    .capitalisation(60_000L)
//                    .build(),
//            Company.builder()
//                    .name("The Hitchhiker's Guide to the Galaxy")
//                    .address("London")
//                    .amount(5000)
//                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("EARTH"))
//                            .findFirst()
//                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
//                    .taxId("123123123")
//                    .established(1980)
//                    .capitalisation(100_000L)
//                    .build(),
//            Company.builder()
//                    .name("Deep Thought")
//                    .address("NY")
//                    .amount(300)
//                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("EARTH"))
//                            .findFirst()
//                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
//                    .taxId("456456456")
//                    .established(-1000)
//                    .capitalisation(90_000L)
//                    .build(),
//            Company.builder()
//                    .name("The Restaurant at the End of the Universe")
//                    .address("Viltvolde VI")
//                    .amount(10)
//                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("VV"))
//                            .findFirst()
//                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
//                    .taxId("789789789")
//                    .established(-2500)
//                    .capitalisation(41_000L)
//                    .build(),
//            Company.builder()
//                    .name("The Vogon Constructor Fleet")
//                    .address("Vogsphere")
//                    .amount(10000)
//                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("VOG"))
//                            .findFirst()
//                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
//                    .taxId("321321321")
//                    .established(-3000)
//                    .capitalisation(8_000L)
//                    .build(),
//            Company.builder()
//                    .name("Magrathea planet builder Ltd")
//                    .address("Magrathea")
//                    .amount(12000)
//                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("MA"))
//                            .findFirst()
//                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
//                    .taxId("654654654")
//                    .established(-5000)
//                    .capitalisation(42_000L)
//                    .build()
//    );

    @ToString.Exclude
    @ManyToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    @Length(min = 9, max = 9)
    @Column(name = "tax_id", nullable = false, unique = true, length = 9)
    private String taxId;

    @Column(name = "established", nullable = false)
    private Integer established;

    @Column(name = "capitalisation", nullable = false)
    private Long capitalisation;
}
