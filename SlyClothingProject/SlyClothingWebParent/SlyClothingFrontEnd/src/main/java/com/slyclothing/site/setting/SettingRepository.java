package com.slyclothing.site.setting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.slyclothing.common.entity.Setting;
import com.slyclothing.common.entity.SettingCategory;

public interface SettingRepository extends JpaRepository<Setting, String> {
	public List<Setting> findByCategory(SettingCategory category);
	
	@Query("SELECT s FROM Setting s WHERE s.category = ?1 OR s.category = ?2")
	public List<Setting> findByTwoCategories(SettingCategory catOne, SettingCategory catTwo);
}
