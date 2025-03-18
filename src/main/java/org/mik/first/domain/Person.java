package org.mik.first.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.mik.first.Const;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter

@Entity
@Table(name = Person.TBL_NAME)
public class Person extends Client{
    public static final String TBL_NAME = "person";

    public static final List<Person> PERSONS = List.of(
            Person.builder()
                    .name("Zaphod Beeblebrox")
                    .address("Betelgeuse City")
                    .amount(42)
                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("BET"))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                    .personalId("123456789")
                    .age(42)
                    .build(),
            Person.builder()
                    .name("Ford Prefect")
                    .address("Betelgeuse City")
                    .amount(142)
                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("BET"))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                    .personalId("987654321")
                    .age(41)
                    .build(),
            Person.builder()
                    .name("Arthur Dent")
                    .address("London")
                    .amount(10042)
                    .country(Country.COUNTRIES.stream()
                            .filter(c->c.getSign().equals("EARTH"))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                    .personalId("123123123")
                    .age(32)
                    .build(),
            Person.builder()
                    .name("Tricia McMillan")
                    .address("Leshoto")
                    .amount(12)
                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("ZA"))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                    .personalId("456456456")
                    .age(23)
                    .build(),
            Person.builder()
                    .name("Humma Kavula")
                    .address("Viltvolde VI")
                    .amount(20042)
                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("VV"))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                    .personalId("789789789")
                    .age(70)
                    .build(),
            Person.builder()
                    .name("Prostetnic Vogon Jeltz")
                    .address("Vogaria")
                    .amount(3000)
                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("VOG"))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                    .personalId("321321321")
                    .age(170)
                    .build(),
            Person.builder()
                    .name("Slartibartfast")
                    .address("Magrathea")
                    .amount(1)
                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("MA"))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                    .personalId("654654654")
                    .age(2000)
                    .build(),
            Person.builder()
                    .name("Marvin the robot")
                    .address("Betelgeuse")
                    .amount(0)
                    .country(Country.COUNTRIES.stream().filter(c->c.getSign().equals("BET"))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                    .personalId("987987987")
                    .age(4)
                    .build()
    );

    @Length(min = 12, max = 12)
    @Column(name = "personal_id", nullable = false, unique = true, length = 12)
    private String personalId;

    @Column(name = "age", nullable = false)
    private Integer age;

}
