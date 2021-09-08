package hello.mvc.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hello.mvc.entity.User;




// h2 in memory works
//@DataJpaTest(properties = {
//		  "spring.test.database.replace=NONE",
//		  "spring.datasource.url=jdbc:h2:mem:test" // works
		  //"spring.datasource.url=jdbc:h2:tcp://localhost/~/test"
//		})

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // loks for application.properties for db connection
public class UserRepositoryTest {
	
	  @Autowired
	    EntityManager entityManager;

		@Autowired
		UserRepository userRepository;
	
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);
	
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
	 }
	 @Test
	 public void testFindByUserName() {
		 log.debug("**************TestSaveUser***************");
		 User u = new User("testmeA", "testyou", true);
		 userRepository.save(u);
		 User u1 = userRepository.findByUsername("testmeA");
		 assertNotNull(u1);
		 assertEquals(u1.getUsername(), u.getUsername());
		 assertEquals(u1.getPassword(), u.getPassword());
	 }
	 @Test
	 public void testDeleteUser() {
		 log.debug("**************TestSaveUser***************");
		 User u = new User("testmeAA", "testyou", true);
		 userRepository.save(u);
		 User u1 = userRepository.findByUsername("testmeAA");
		 assertNotNull(u1);
		
		 userRepository.delete(u1);
		 User u3 = userRepository.findByUsername("testmeAA");
		 assertNull(u3);
	 }
	 
	 @Test
	 public void testDeleteById() {
		 log.debug("**************TestSaveUser***************");
		 User u = new User("testmeAAA", "testyou", true);
		 userRepository.save(u);
		 User u1 = userRepository.findByUsername("testmeAAA");
		 assertNotNull(u1);
		
		 userRepository.deleteById(u1.getId());
		 User u3 = userRepository.findByUsername("testmeAA");
		 assertNull(u3);
	 }
	 
	 @Test
	 public void testFindAll() {
		 log.debug("**************TestSaveUser***************");
		 User u = new User("testmeAAAA", "testyou", true);
		 userRepository.save(u);
		
		 assertNotNull(userRepository.findAll());
		
	 }
	 
	
}
