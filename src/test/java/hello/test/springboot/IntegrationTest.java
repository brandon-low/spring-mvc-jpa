package hello.test.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import hello.mvc.config.MvcWebConfig;
import hello.mvc.entity.User;
import hello.mvc.repository.UserRepository;
import hello.test.mvc.service.FakeServiceController;
import hello.test.mvc.service.FakeServiceImpl;

/**
 * 
 * Test End to end using spring boot test JUNIT5
 

 */
//@Import(hello.mvc.repository.UserRepository.class) use this to import certain bean on in component
//@Import(other.namespace.Foo.class) 
// NOT NEEDED @SpringBootTest included it @ExtendWith(SpringExtension.class)
//@ExtendWith(SpringExtension.class)

@SpringBootTest
@AutoConfigureMockMvc			// adds MockMvc
@ContextConfiguration(classes = {MvcWebConfig.class})
//@AutoConfigureTestDatabase	// This allows us to run the test against a real database instead of the embedded one.
public class IntegrationTest {
	private static final Logger log = LoggerFactory.getLogger(IntegrationTest.class);
	
	@Autowired	private MockMvc mockMvc;
	@Autowired	UserRepository userRepository;
	@Autowired  DataSource dataSource;
	@Autowired  EntityManager entityManager;
	
	@Test
	public void testInjectedComponentsAreNotNull(){
		log.debug("**************testInjectedComponentsAreNotNull***************");
		assertNotNull(dataSource);
		//assertNotNull(jdbcTemplate);
		assertNotNull(entityManager);
		assertNotNull(userRepository);
	}
	
	@Test
	public void testSaveUser() {
		 log.debug("**************TestSaveUser***************");
		 User u = new User("testme", "testyou", true);
		 userRepository.save(u);
		 userRepository.flush();
		 User u1 = userRepository.findByUsername("testme");
		 assertNotNull(u1);
		 assertEquals(u1.getUsername(), u.getUsername());
		 assertEquals(u1.getPassword(), u.getPassword());
		 
		 /*
		 assertThat(tutorial).hasFieldOrPropertyWithValue("title", "Tut title");
		    assertThat(tutorial).hasFieldOrPropertyWithValue("description", "Tut desc");
		    assertThat(tutorial).hasFieldOrPropertyWithValue("published", true);
		    Tutorial tut2 = new Tutorial("Tut#2", "Desc#2", false);
		    entityManager.persist(tut2);

		    Tutorial tut3 = new Tutorial("Tut#3", "Desc#3", true);
		    entityManager.persist(tut3);

		    Iterable<Tutorial> tutorials = repository.findAll();

		    assertThat(tutorials).hasSize(3).contains(tut1, tut2, tut3);
		    assertThat(foundTutorial).isEqualTo(tut2);
		    Iterable<Tutorial> tutorials = repository.findByPublished(true);

		    assertThat(tutorials).hasSize(2).contains(tut1, tut3);
		    entityManager.persist(new Tutorial("Tut#1", "Desc#1", true));
		    entityManager.persist(new Tutorial("Tut#2", "Desc#2", false));

		    repository.deleteAll();

		    assertThat(repository.findAll()).isEmpty();
		    */
	 }
	
	/*  // do
	 @MockBean			// not from db 
	  private UserRepository userRepository;
	*/
	// write some test case
	/*
	  @Autowired
	  private ObjectMapper objectMapper;

	  @Autowired
	  private UserRepository userRepository;

	  @Test
	  void registrationWorksThroughAllLayers() throws Exception {
	    UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");

	    mockMvc.perform(post("/forums/{forumId}/register", 42L)
	            .contentType("application/json")
	            .param("sendWelcomeMail", "true")
	            .content(objectMapper.writeValueAsString(user)))
	            .andExpect(status().isOk());

	    UserEntity userEntity = userRepository.findByName("Zaphod");
	    assertThat(userEntity.getEmail()).isEqualTo("zaphod@galaxy.net");
	  }
	  */
}
