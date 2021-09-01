package hello.mvc.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.io.Serializable;

import javax.persistence.*;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "contacts")
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
//@Table(name="address" , catalog = "test")
public class Address implements Serializable {  

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// internationalised in the messages
	@Size(min=5, max=20, message="{address.name.size}")
	private String name;
	
	private String address;
	private String phone;
	
	@NotEmpty(message = "{address.email.required}")
	@Email(message ="Invalid email address")
	private String email;

	public Address() {	}
	
	public Address(String name, String address, String phone, String email) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
    public Long getId() {
        return this.id;
    }

    public void setId(Long _id) {
        this.id = _id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Address [_id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email="
				+ email + "]";
	}

}
