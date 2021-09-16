package hello.mvc.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hello.test.context.AppConfig;



/**
 * 
 * @author brandon
 * You need to have this in your path 
 @SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
and 

with @ContextConfiguration(classes = {AppConfig.class})
you need this
@Configuration
@ComponentScan(basePackages = {"hello.generate", "hello.generate.repository", "hello.generate.entity"})
public class AppConfig {
}
without
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase( replace = Replace.NONE)
//@ContextConfiguration(classes = {AppConfig.class})  // without. it will scan everything
public class ConnectTest {
	@Autowired	EntityManager entityManager;
	@Autowired	DataSource dataSource;
	private static final Logger LOG = LoggerFactory.getLogger(ConnectTest.class);
	
	@Test
	public void have_datasource_entitymanager(){
		LOG.debug("**************testInjectedComponentsAreNotNull***************");
		assertNotNull(dataSource);
		assertNotNull(entityManager);
		assertTrue(true, "test hello");
	}
}
