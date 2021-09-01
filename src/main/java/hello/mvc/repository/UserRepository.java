package hello.mvc.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hello.mvc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 @Query("select u from User u where u.username = ?1")
	 User findByUsername(String username);
	 
	 @Query("SELECT u FROM User u WHERE u.accountNonLocked = ?1")
	 Collection<User> findAllUserAccountNonLocked(boolean accountNonLocked);
	 
	 @Query("select u from User u where u.username = ?1 and u.password = ?2")
	 User findByUsernamePassword(String username, String pasword);
	 
	 
	/*
	List<User> findAll();

    User findByid(Long id);

    //@Query("{ username : ?0 }")
	User findByUsername(String username);
    
    //@Query("{ accountNonLocked : ?0 }")
    List<User> findAllUserAccountNonLocked(boolean accountNonLocked);
    */

}

