package org.mik.first.domain;

import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;


import java.util.List;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter

@Entity
@Table(name = Country.TBL_NAME)
@SequenceGenerator(name ="SEQ", sequenceName = "country_seq", allocationSize = 1)

public class Country extends AbstractDomain<Long> {

    public static final String TBL_NAME = "countries";
    public static final String COUNTRY_NOT_FOUND = "Country not found";

    public static final List<Country> COUNTRIES=List.of(
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
    );

    @Length(min = 2, max = 50)
    @NotBlank
    @Column(name = "name",nullable = false, unique = true,length = 50)
    private String name;

    @Length(min = 1, max = 3)
    @NotBlank
    @Column(name ="sign", nullable = false,unique = true,length = 3)
    private String sign;

}
