package hello.mvc.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.binary.Base32;

//import org.springframework.security.crypto.password.PasswordEncoder;

//import hello.entity.Address;
import hello.mvc.entity.User;
import hello.mvc.entity.UserRole;

public interface UserService {

	void create(User user);
	void update(User user);
	void delete(Long id);
	Collection<User> getAllUsers();
	User getUserById(Long id);
	User getUserByUsername(String username);
	User getUserByUsernamePassword(String username, String password);
	boolean createUser(String username, String password, boolean enabled, 
						boolean accountNonExpired , boolean credentialsNonExpired, 
						boolean accountNonLocked, int attempts, HashSet<UserRole> roles) ;
	boolean createDefaultUsers() ;
	public Base32 getPasswordEncoder();
	Collection<User> findAllUserAccountNonLocked(boolean accountNonLocked);
	void updateUserAttempt(String username);
	void resetUserAttempt(User user);
	void resetUserAttempt(String username);
	void resetAllLockedUsers();
	void lockAllUsers();

}
