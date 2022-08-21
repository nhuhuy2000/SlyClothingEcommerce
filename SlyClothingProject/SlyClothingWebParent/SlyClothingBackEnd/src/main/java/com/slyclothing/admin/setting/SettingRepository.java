package com.slyclothing.admin.setting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slyclothing.common.entity.Setting;
import com.slyclothing.common.entity.SettingCategory;

public interface SettingRepository extends JpaRepository<Setting, String> {
	public List<Setting> findByCategory(SettingCategory category);
}
