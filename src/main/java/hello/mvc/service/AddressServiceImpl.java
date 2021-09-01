package hello.mvc.service;


import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import hello.mvc.entity.*;
import hello.mvc.repository.*;

@Service("AddressService")
@Transactional
public class AddressServiceImpl implements AddressService {
    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Autowired
	AddressRepository addressRepository;
	
	public void createAddress(Address address) {
		addressRepository.save(address);
	}

	public List<Address> getAllAddresses() {
		return addressRepository.findAll();

	}

	public void deleteAddress(Long id) {
        Address address = addressRepository.getById(id);
        if (address != null){
		    addressRepository.delete(address);
        }
	}

	@Override
	public Address getAddressById(Long id) {
		return addressRepository.getById(id);
	}

	@Override
	public void updateAddress(Address address) {
		
        Address addressInDB = addressRepository.getById(address.getId());
        
        logger.info("update from " + addressInDB + " to " + address);
        addressInDB.setName(address.getName());
        addressInDB.setAddress(address.getAddress());
        addressInDB.setPhone(address.getPhone());
        addressInDB.setEmail(address.getEmail());
        
        addressRepository.save(addressInDB);
	}
	
	

}