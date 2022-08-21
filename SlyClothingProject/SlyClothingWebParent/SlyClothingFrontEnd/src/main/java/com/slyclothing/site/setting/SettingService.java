package com.slyclothing.site.setting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slyclothing.common.entity.Setting;
import com.slyclothing.common.entity.SettingCategory;

@Service
public class SettingService {
	@Autowired 
	private SettingRepository settingRepository;
	
	
	public List<Setting> getGeneralSettings() {
		return settingRepository.findByTwoCategories(SettingCategory.GENERAL, SettingCategory.CURRENCY);
		
		
	}
	
}
