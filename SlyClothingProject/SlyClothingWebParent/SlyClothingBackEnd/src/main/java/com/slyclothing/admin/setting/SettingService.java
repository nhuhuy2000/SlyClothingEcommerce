package com.slyclothing.admin.setting;

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
	
	public List<Setting> listAllSettings(){
		return settingRepository.findAll();
	}
	public GeneralSettingBag getGeneralSettings() {
		List<Setting> generalSettings = settingRepository.findByCategory(SettingCategory.GENERAL);
		List<Setting> settings = new ArrayList<>();
		List<Setting> currencySettings = settingRepository.findByCategory(SettingCategory.CURRENCY);
		
		settings.addAll(generalSettings);
		settings.addAll(currencySettings);
		return new GeneralSettingBag(generalSettings);
		
	}
	public void saveAll(Iterable<Setting> settings) {
		settingRepository.saveAll(settings);
	}
}
