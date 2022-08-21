package com.slyclothing.admin.country;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.slyclothing.common.entity.Country;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CountryRepositoryTests {
	
		@Autowired
		private CountryRepository repository;
		
		@Test
		@Disabled
		public void testCreateCountry() {
			Country country = repository.save(new Country("Vietnam", "VN"));
			assertThat(country).isNotNull();
			assertThat(country.getId()).isGreaterThan(0);
		}
		@Test
		@Disabled
		public void testListCountries() {
			List<Country> listCountries = repository.findAllByOrderByNameAsc();
			listCountries.forEach(System.out::println);
			
			assertThat(listCountries.size()).isGreaterThan(0);
		}
		@Test
		@Disabled
		public void testUpdateCountry() {
			Integer id = 1;
			String name = "Republic of India";
			Country country = repository.findById(id).get();
			country.setName(name);
			
			Country updatedCountry = repository.save(country);
			assertThat(updatedCountry.getName()).isEqualTo(name);
		}
		@Test
		@Disabled
		public void testGetCountry() {
			Integer id = 2;
			Country country = repository.findById(id).get();
			assertThat(country).isNotNull();
		}
		@Test
		@Disabled
		public void testDeleteCountry() {
			repository.deleteById(2);
			
			Optional<Country> findById = repository.findById(2);
			assertThat(findById.isEmpty());
		}
}
