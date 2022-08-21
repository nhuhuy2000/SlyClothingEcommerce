package com.slyclothing.admin.country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slyclothing.common.entity.Country;

@RestController
public class CountryRestController {
	
	@Autowired
	private CountryRepository repository;
	
	@GetMapping("/countries/list")
	public List<Country> listAll(){
		return repository.findAllByOrderByNameAsc();
	}
	
}
