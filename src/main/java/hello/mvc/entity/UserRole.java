package hello.mvc.entity;

import java.io.Serializable;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.*;

//@Document(collection = "user_roles")
@Entity
//@Table(name = "user_roles", catalog = "test", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "username" }))
public class UserRole implements Serializable { 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	 //@ManyToOne(fetch = FetchType.LAZY, optional = false)
	 //		@JoinColumn(name = "user_id", nullable = false)
	 //private User user;
	 
	private String role;
	//private User user;

	public UserRole() {
	}
	public UserRole(String role) {
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	//@Column(name = "role", nullable = false, length = 45)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}


/*
	public UserRole(User user, String role) {
		this.user = user;
		this.role = role;
	}
*/
	//@Id
	//@GeneratedValue(strategy = IDENTITY)
	//@Column(name = "user_role_id", unique = true, nullable = false)
	//public Integer getUserRoleId() {
	//	return this.userRoleId;
	//}

	//public void setUserRoleId(Integer userRoleId) {
	//	this.userRoleId = userRoleId;
	//}

	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "username", nullable = false)
	//public User getUser() {
	//	return this.user;
	//}

	//public void setUser(User user) {
	//	this.user = user;
	//}

	
	
	
}