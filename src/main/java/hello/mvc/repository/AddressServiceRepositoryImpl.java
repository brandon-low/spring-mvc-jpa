package hello.mvc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import hello.mvc.entity.Address;
import hello.mvc.entity.Page;

public class AddressServiceRepositoryImpl implements AddressRepositoryCustom {

	private final JdbcTemplate jdbcTemplate;
	 
	@Autowired
	public AddressServiceRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public int pageSize(Address address) {return 0; }
	public List search(Address address, int offset, int pageSize) {
		return null;
	}
	
	public Page search(Page page) { return null ;}
	/*
	private List<Criteria> buildCriteria(Address address) {
		List<Criteria> criteria = new ArrayList<>();
		
		if (address.getName() != null) {
			criteria.add(Criteria.where("name").regex("/"+address.getName()+"/") );
		}
		if (address.getAddress() != null) {
			criteria.add(Criteria.where("address").regex("/"+address.getAddress()+"/") );
				
		}
		if (address.getEmail() != null) {
			criteria.add(Criteria.where("email").regex("/"+address.getEmail()+"/") );
		}
			
		return criteria;
	}
	
	public int pageSize(Address address) {
		final Query query = new Query();
		final List<Criteria> criteria = buildCriteria(address);
		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		}
		return mongoTemplate.find(query, Address.class).size();
	}
	
	public List search(Address address, int offset, int pageSize) {
		final Query query = new Query();
		final List<Criteria> criteria = buildCriteria(address);
		final Pageable pageableRequest = PageRequest.of(offset, pageSize);
		
		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		}
	
		query.with(pageableRequest);
		
		return mongoTemplate.find(query, Address.class);

	}
	
	public Page search(Page page) {
		if (!(page.getForm() instanceof Address))
			return page;
			
		Address address = (Address) page.getForm();
		
		page.setRowCount(pageSize(address));
		
		List l = search(address, page.getOffsetPosition(), page.getPageSize());
		
		page.setCollections(l);
		return page;
	}
	*/
	
}
