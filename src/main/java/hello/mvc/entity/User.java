package hello.mvc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.*;


//import org.springframework.data.annotation.Id;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;

//import org.springframework.data.mongodb.core.mapping.Document;


/*
 * import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
@NotNull(message = "Name cannot be null")
private String name;

@AssertTrue
private boolean working;

@Size(min = 10, max = 200, message = "About Me must be between 10 and 200 characters")
private String aboutMe;

@Min(value = 18, message = "Age should not be less than 18")
@Max(value = 150, message = "Age should not be greater than 150")
private int age;

@Email(message = "Email should be valid")
private String email;

Set<ConstraintViolation<User>> violations = validator.validate(user);
for (ConstraintViolation<User> violation : violations) {
    log.error(violation.getMessage()); 
}
*/

//@Entity
//@Table(name = "users", catalog = "test")
//@Document(collection = "users")
@Entity
//@Table(name="user", catalog = "test")
public class User implements Serializable { 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	private String password;
	private boolean enabled;
	private Locale locale; 	//= Locale.US;	// default

	private boolean accountNonExpired ; 
	private boolean credentialsNonExpired;
	private boolean accountNonLocked;  // if true, its not locked else false it means it is locked status
	private int attempts;
	private Date lastModified;
	
	private Date createTimestamp;
	private Date updateTimestamp;
	
	 @OneToMany(targetEntity=UserRole.class,
			 fetch = FetchType.EAGER,
			 	cascade = CascadeType.ALL)
	 //, fetch = FetchType.LAZY,	cascade = CascadeType.ALL)  
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	
	public User() {
	}

	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String username, String password, boolean enabled, Set<UserRole> userRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		//this.userRole = userRole;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//@Id
	//@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//@Column(name = "password", nullable = false, length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}


	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public Locale getLocale() {
		if (locale == null) {
			return Locale.US;
		}
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", accountNonExpired=" + accountNonExpired + ", credentialsNonExpired=" + credentialsNonExpired
				+ ", accountNonLocked=" + accountNonLocked + ", attempts=" + attempts + ", lastModified=" + lastModified
				+ ", createTimestamp=" + createTimestamp + ", updateTimestamp=" + updateTimestamp + ", userRole="
				+ userRole + "]";
	}

	
	
	
	

}
