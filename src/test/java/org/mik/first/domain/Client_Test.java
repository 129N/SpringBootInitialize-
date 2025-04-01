package org.mik.first.domain;

import org.mik.first.Const;
import org.springframework.data.jpa.repository.JpaRepository;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.LinkedList;
import java.util.List;


public class Client_Test extends AbstractDomain_Test<Long, Client>{

    public static List<Client> TEST_DATA = new LinkedList<>(
            List.of(
                    Client.builder()
                            .name("Zaphod Beeblebrox")
                            .address("Betelgeuse City")
                            .amount(42)
                            .country(Country_test.TEST_DATA.stream().filter(c->c.getSign().equals("BET"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("Ford Prefect")
                            .address("Betelgeuse City")
                            .amount(142)
                            .country(Country_test.TEST_DATA.stream().filter(c->c.getSign().equals("BET"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("Arthur Dent")
                            .address("London")
                            .amount(10042)
                            .country(Country_test.TEST_DATA.stream()
                                    .filter(c->c.getSign().equals("EARTH"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("Tricia McMillan")
                            .address("Leshoto")
                            .amount(12)
                            .country(Country_test.TEST_DATA.stream().filter(c->c.getSign().equals("ZA"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("Humma Kavula")
                            .address("Viltvolde VI")
                            .amount(20042)
                            .country(Country_test.TEST_DATA.stream().filter(c->c.getSign().equals("VV"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("Prostetnic Vogon Jeltz")
                            .address("Vogaria")
                            .amount(3000)
                            .country(Country_test.TEST_DATA.stream().filter(c->c.getSign().equals("VOG"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("Slartibartfast")
                            .address("Magrathea")
                            .amount(1)
                            .country(Country_test.TEST_DATA.stream().filter(c->c.getSign().equals("MA"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                            .build(),
                    Client.builder()
                            .name("Marvin the robot")
                            .address("Betelgeuse")
                            .amount(0)
                            .country(Country_test.TEST_DATA.stream().filter(c->c.getSign().equals("BET"))
                                    .findFirst()
                                    .orElseThrow(()->new RuntimeException(Const.COUNTRY_NOT_FOUND)))
                            .build()
            )

    );

    public Client_Test(PostgreSQLContainer<?> postgres, JpaRepository<Client, Long> repository){
        super(postgres, repository);
    }
    @Override
    protected Class<Client> getClazz(){
        return Client.class;
    }

    @Override
    protected List<ConstrainViolationEntity<Long,Client>> getConstraintViolationEntities(){
        return List.of(
                new ConstrainViolationEntity<>(
                        Client.builder()
//                                .name("asd")
                                .country(Country_test.TEST_DATA.get(0))
                                .address("address")
                                .amount(0)
                                .build(),
                        Exception.class
                ),
                new ConstrainViolationEntity<>(
                        Client.builder()
                                .name("asd")
                                .country(Country_test.TEST_DATA.get(0))
                                //                              .address("address")
                                .amount(0)
                                .build(),
                        Exception.class
                ),
                new ConstrainViolationEntity<>(
                        Client.builder()
                                .name("asd")
                                .country(Country_test.TEST_DATA.get(0))
                                .address("address")
                                //                              .amount(0)
                                .build(),
                        Exception.class
                )
        );

    }


    @Override
    protected List<ValidEntity<Long, Client>> getValidEntities(){
        return TEST_DATA.stream()
                .map(c-> new ValidEntity<>(c,
                       e-> {
                           e.setName(e.getName()+"_m");
                           e.setAddress(e.getAddress()+"_m");
                           e.setAmount(e.getAmount()+1);
                           e.setCountry(Country_test.TEST_DATA.get(0));

                       })

                ).toList();
    }

}



