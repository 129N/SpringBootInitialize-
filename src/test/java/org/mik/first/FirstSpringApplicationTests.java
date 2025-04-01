package org.mik.first;

import org.junit.jupiter.api.*;
import org.mik.first.entity.*;
import org.mik.first.repository.*;
import org.mik.first.service.ClientService;
import org.mik.first.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ImportTestcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(initializers = FirstSpringApplicationTests.TestContainersInitializer.class)
class FirstSpringApplicationTests {

    private static final boolean REST_TEST = false;
    private static final boolean DB_STRUCT_FROM_SQL = false;

    static class TestContainersInitializer implements
            ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                            "spring.datasource.url=" + postgresService.getJdbcUrl(),
                            "spring.datasource.username=" + postgresService.getUsername(),
                            "spring.datasource.password=" + postgresService.getPassword(),
                            "spring.datasource.driver-class-name=" + postgresService.getDriverClassName(),
                            "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect",
                            DB_STRUCT_FROM_SQL
                                    ? "spring.jpa.hibernate.ddl-auto=none"
                                    : "spring.jpa.hibernate.ddl-auto=create-drop",
                            "spring.jpa.show-sql=false",
                            "spring.jpa.properties.hibernate.format_sql=true",
                            "spring.jpa.properties.hibernate.use_sql_comments=false",
                            "spring.jpa.properties.hibernate.type=info",
                            "spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true"
                    )
                    .applyTo(applicationContext.getEnvironment());
        }
    }

    @Container
    private static PostgreSQLContainer<?> postgresService = DB_STRUCT_FROM_SQL
            ? new PostgreSQLContainer<>("postgres:16-alpine")
                .withCommand("sql/data.sql")
                .withInitScript("sql/data.sql")
            : new PostgreSQLContainer<>("postgres:16-alpine");

    @LocalServerPort
    private int port;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ClientService clientService;

    @BeforeAll
    public static void beforeAll() {
        try {
            postgresService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void afterAll() {
        postgresService.stop();
    }

    @Test
    @Order(0)
    void entityTests() {
        new CountryEntityTest(countryRepository).start();
        new ClientEntityTest(clientRepository).start();
        new JobEntityTest(jobRepository).start();
        new PersonEntityTest(personRepository).start();
        new CompanyEntityTest(companyRepository).start();
    }

    @Test
    @Order(1)
    void serviceTests() {
        new ServiceTest(clientService).testAddJob();
    }


}
