package com.slyclothing.site.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slyclothing.common.entity.Country;
import com.slyclothing.site.country.CountryRepository;

@Service
public class CustomerService {
//	private CountryRepository countryRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Country> lisAllCountries(){
		return countryRepository.findAllByOrderByNameAsc();
	}
}
