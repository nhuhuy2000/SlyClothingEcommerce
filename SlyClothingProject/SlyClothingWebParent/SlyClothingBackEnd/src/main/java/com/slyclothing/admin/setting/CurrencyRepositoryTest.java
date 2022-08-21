package com.slyclothing.admin.setting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.slyclothing.common.entity.Currency;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CurrencyRepositoryTest {
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Test
	@Disabled
	public void testCreateCurrencies() {
		List<Currency> listCurrencies = Arrays.asList(
				new Currency("Vietnamese dong", "đ", "VND"),
				new Currency("United States Dollar", "$", "USD")
				);
		currencyRepository.saveAll(listCurrencies);
		
		List<Currency> list = currencyRepository.findAll();
		
		assertThat(list).isNotNull();
	}
	
	public void testListAllOrderByNameAsc() {
		List<Currency> currencies = currencyRepository.findAllByOrderByNameAsc();
		
		currencies.forEach(System.out::println);
		
		assertThat(currencies.size()).isGreaterThan(0);
	}
}
