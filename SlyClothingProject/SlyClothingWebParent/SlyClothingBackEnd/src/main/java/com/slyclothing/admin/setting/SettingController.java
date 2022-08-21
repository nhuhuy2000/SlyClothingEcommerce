package com.slyclothing.admin.setting;




import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.slyclothing.admin.utility.FileUploadUtil;
import com.slyclothing.common.entity.Currency;
import com.slyclothing.common.entity.Setting;


@Controller
public class SettingController {
	
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@GetMapping("/settings")
	public String listAll(Model model) {
		List<Setting> listSettings = settingService.listAllSettings();
		List<Currency> listCurrencies = currencyRepository.findAllByOrderByNameAsc();
		for (Setting setting : listSettings) {
			model.addAttribute(setting.getKey(), setting.getValue());
		}
		model.addAttribute("listCurrencies", listCurrencies);
		return "settings/settings";
	}
	@PostMapping("/settings/save_general")
	public String saveGeneralSettings(@RequestParam("fileImage") MultipartFile multipartFile,
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		GeneralSettingBag settingBag = settingService.getGeneralSettings();
		saveSiteLogo(multipartFile, settingBag);
		saveCurrencySymbol(request, settingBag);
		updateSettingValuesFromForm(request, settingBag.list());
		redirectAttributes.addFlashAttribute("message", "General settings have been saved");
		return "redirect:/settings";
	}
	private void saveSiteLogo(MultipartFile multipartFile, GeneralSettingBag settingBag) throws IOException {
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			String value = "/site-logo/" + fileName;
			settingBag.updateSiteLogo(value);
			String uploadDir = "../site-logo/";
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}
	}
	private void saveCurrencySymbol(HttpServletRequest httpServletRequest, GeneralSettingBag settingBag){
		Integer currencyId = Integer.parseInt(httpServletRequest.getParameter("CURRENCY_ID"));
		Optional<Currency> findByIdResult = currencyRepository.findById(currencyId);
		if(findByIdResult.isPresent()) {
			Currency currency = findByIdResult.get();
			settingBag.updateCurrencySymbol(currency.getSymbol());
		}
		
	}
	private void updateSettingValuesFromForm(HttpServletRequest request, List<Setting> listSettings) {
		for(Setting setting : listSettings) {
			String value = request.getParameter(setting.getKey());
			if(value != null) {
				setting.setValue(value);
			}
		}
		settingService.saveAll(listSettings);
	}
}
