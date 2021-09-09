package hello.mvc.repository;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.sql.DataSource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
		
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

	@Autowired  DataSource dataSource;
	@Autowired  EntityManager entityManager;
	@Autowired	UserRepository userRepository;
	
	@Test
	public void testInjectedComponentsAreNotNull(){
		log.debug("**************testInjectedComponentsAreNotNull***************");
		assertNotNull(dataSource);
		//assertNotNull(jdbcTemplate);
		assertNotNull(entityManager);
		assertNotNull(userRepository);
	}
	 
	@Test
	public void testCustomQuery() {
		log.debug("Custom Query Test");
		/*
		 * TypedQuery<UserEntity> typedQuery
		 String queryStr = "select id,name from users where roll_no = ?1";
	     Query query = entityManager.createNativeQuery(queryStr);
	            query.setParameter(1, rollNo);
	     */
		try {
			entityManager.persist(new User("CCCgdhdh","Cisisi", true));
			String queryString = "select u from User u";
			Query query = entityManager.createQuery(queryString);
			List<User[]> list = query.getResultList();
			
			assertNotNull(list);
		} catch (Exception e) {
			fail(e);
		}
	}
	
	
	@Test
	public void testSaveUserPersistence() {
		 User u1 = new User("AAA", "testyou", true);
		 User u2 = new User("BBB", "testyou", true);
		 
		 List<User> users1 = userRepository.findAll();
		 entityManager.persist(u1);
		 entityManager.persist(u2);
		 List<User> users2 = userRepository.findAll();
		 assertTrue((users2.size() - users1.size()) == 2);
		
	}
	@Test
	public void testDeleteAll() {
		entityManager.persist(new User("CCC","Cisisi", true));
		entityManager.persist(new User("DDD","Cisisi", true));
		assertTrue( (userRepository.findAll().size() > 0)) ;
		userRepository.deleteAll();
		assertTrue( (  userRepository.findAll() == null || userRepository.findAll().size() == 0)) ;
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
