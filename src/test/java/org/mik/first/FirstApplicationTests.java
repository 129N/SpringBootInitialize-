package org.mik.first;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.*;
import org.mik.first.domain.Client_Test;
import org.mik.first.domain.Country_test;
import org.mik.first.repository.ClientRepository;
import org.mik.first.repository.Countryrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer;
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
@ContextConfiguration(initializers = FirstApplicationTests.TestContainerInitializer.class)
class FirstApplicationTests {
	static class TestContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext){
			TestPropertyValues.of(
					"spring.datasource.url"+PostgresService.getJdbcUrl(),
					"spring.datasource.username"+PostgresService.getUsername(),
					"spring.datasource.password"+PostgresService.getPassword(),
					"spring.datasource.driver-classname"+PostgresService.getDriverClassName(),
					"spring.jpa.database-platform=org.hibernate.dialect.PostgresqlDialect",
					"spring.jpa.hibernate.ddl-auto=create-drop"
					).applyTo(applicationContext.getEnvironment());
		}
	}

	@Container
	private static PostgreSQLContainer<?> PostgresService=new PostgreSQLContainer<>("postgres-16:alpine");

	@Autowired
	Countryrepository countryrepository;
	@Autowired
	ClientRepository clientRepository;


	@BeforeAll
	public  static void beforeALL(){
		try{
			PostgresService.start();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}



	@AfterAll
	public static void afterAll(){
		PostgresService.stop();
	}



	@Test
	@Order(0)
	void entityTests() {
		new Country_test(PostgresService, countryrepository).entityTests();
		new Client_Test(PostgresService, clientRepository).entityTests();
	}

}
