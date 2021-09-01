package hello.mvc.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.mvc.entity.*;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findAll();

    Address findByid(Long id);

	Address findByName(String name);

}
