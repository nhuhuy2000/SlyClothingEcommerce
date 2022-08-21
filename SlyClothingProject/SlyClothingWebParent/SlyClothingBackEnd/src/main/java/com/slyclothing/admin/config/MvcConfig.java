package com.slyclothing.admin.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		String dirNameString = "admin-photos";
//		Path userPhotoDirPath = Paths.get(dirNameString);
//		String userPhotosPathString = userPhotoDirPath.toFile().getAbsolutePath();
//
//		registry.addResourceHandler("/" + dirNameString + "/**")
//				.addResourceLocations("file:/" + userPhotosPathString + "/");
//		String categoryImagesDirName = "../category-images";
//		Path categoryImagesDir = Paths.get(categoryImagesDirName);
//		String categoryImagesPath = categoryImagesDir.toFile().getAbsolutePath();
//
//		registry.addResourceHandler("/category-images/**")
//				.addResourceLocations("file:/" + categoryImagesPath + "/");
//		///
//		String brandLogosDirName = "../brand-logos";
//		Path brandLogosDir = Paths.get(brandLogosDirName);
//		
//		String brandLogosPath = brandLogosDir.toFile().getAbsolutePath();
//		
//		registry.addResourceHandler("/brand-logos/**")
//				.addResourceLocations("file:/" + brandLogosPath + "/");
//		
//	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("admin-photos", registry);
		exposeDirectory("../category-images", registry);
		exposeDirectory("../brand-logos", registry);
		exposeDirectory("../product-images", registry);
		exposeDirectory("../site-logo", registry);
	}
	private void exposeDirectory(String pathPattern, ResourceHandlerRegistry registry) {
		Path path = Paths.get(pathPattern);
		String absolutePath = path.toFile().getAbsolutePath();
		
		String logicalPath = pathPattern.replace("../", "") + "/**";
		
		registry.addResourceHandler(logicalPath)
		.addResourceLocations("file:/" + absolutePath + "/");
	}
	
}
