package com.slyclothing.site.customer;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.slyclothing.common.entity.Country;
import com.slyclothing.common.entity.Customer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void testCreateCustomer1() {
		Integer countryId = 123;
		Country country = entityManager.find(Country.class, countryId);
		Customer customer = new Customer();
	
		customer.setCountry(country);
		customer.setName("HUYHUY");
		customer.setPassword("password1232");
		customer.setEmail("nhuhuy2000@gmail.com2");
		customer.setPhoneNumber("01234567892");
		
		Customer savedCustomer = customerRepository.save(customer);
		assertThat(savedCustomer).isNotNull();
		
	}
}
