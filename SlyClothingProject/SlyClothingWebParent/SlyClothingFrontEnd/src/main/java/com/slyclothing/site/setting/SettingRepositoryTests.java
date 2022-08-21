package com.slyclothing.site.setting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.slyclothing.common.entity.Setting;
import com.slyclothing.common.entity.SettingCategory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SettingRepositoryTests {
	@Autowired
	SettingRepository settingRepository;
	@Test
	@Disabled
	public void testCreateGeneralSettings() {
		//Setting siteName = new Setting("SITE_NAME", "SlyClothing", SettingCategory.GENERAL);
		Setting siteLogo = new Setting("SITE_LOGO", "SlyClothing.png", SettingCategory.GENERAL);
		Setting copyRight = new Setting("COPYRIGHT", "Copyright (C) 2022 SlyClothing Ltd.", SettingCategory.GENERAL);
		settingRepository.saveAll(List.of(siteLogo,copyRight));
		
		List<Setting> findAll = settingRepository.findAll();
		assertThat(findAll).hasSizeGreaterThan(3);
	}
	@Test
	@Disabled
	public void testListSettingsByCategory() {
		List<Setting> settings = settingRepository.findByCategory(SettingCategory.GENERAL);
		settings.forEach(System.out::println);
	}
	@Test
	public void testFindByTwoCategories() {
		List<Setting> settings = settingRepository.findByTwoCategories(SettingCategory.GENERAL, SettingCategory.CURRENCY);
		
		assertThat(settings).size().isGreaterThan(0);
	}
}
