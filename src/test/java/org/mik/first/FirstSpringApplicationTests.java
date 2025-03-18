package org.mik.first;

import org.junit.jupiter.api.*;
import org.mik.first.domain.ClientTest;
import org.mik.first.domain.CountryTest;
import org.mik.first.repository.ClientRepository;
import org.mik.first.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ImportTestcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(initializers = FirstSpringApplicationTests.TestContainerInitializer.class)
class FirstSpringApplicationTests {

	static class TestContainerInitializer
			implements ApplicationContextInitializer<ConfigurableApplicationContext> {


		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			TestPropertyValues.of(
				"spring.datasource.url="+postgresService.getJdbcUrl(),
					  "spring.datasource.username="+postgresService.getUsername(),
					  "spring.datasource.password="+postgresService.getPassword(),
					  "spring.datasource.driver-classname="+postgresService.getDriverClassName(),
					  "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect",
					  "spring.jpa.hibernate.ddl-auto=create-drop"
			).applyTo(applicationContext.getEnvironment());
		}
	}

	@Container
	private static PostgreSQLContainer<?> postgresService=new PostgreSQLContainer<>("postgres:16-alpine");

	@Autowired
	CountryRepository countryRepository;
	@Autowired
	ClientRepository clientRepository;

	@BeforeAll
	public static void beforeAll() {
		try {
			postgresService.start();
		}
		catch (Exception e) {
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
		new CountryTest(postgresService, countryRepository).entityTests();
		new ClientTest(postgresService, clientRepository).entityTests();
	}

}
