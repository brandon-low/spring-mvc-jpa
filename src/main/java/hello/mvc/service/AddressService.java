package hello.mvc.service;

import java.util.List;

import hello.mvc.entity.Address;

//import com.osintegrators.example.Address;

public interface AddressService {

	void createAddress(Address add);

	void deleteAddress(Long id);

	List<Address> getAllAddresses();

	Address getAddressById(Long id);

	void updateAddress(Address address);

}
