package com.slyclothing.admin.product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.slyclothing.admin.utility.FileUploadUtil;
import com.slyclothing.common.entity.Product;
import com.slyclothing.common.entity.ProductImage;

public class ProductSaveHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductSaveHelper.class);
	static void deleteExtraImageWeredRemovedOnForm(Product product) {
		// TODO Auto-generated method stub
		String extraImageDir = "../product-images/" + product.getId() + "/extras";
		Path dirPath = Paths.get(extraImageDir);
		try {
			Files.list(dirPath).forEach(file -> {
				String fileName = file.toFile().getName();
				if(!product.containsImageName(fileName)) {
					try {
						Files.delete(file);
						LOGGER.info("Deleted extra image: "+fileName);
					}catch (IOException e) {
						// TODO: handle exception
						LOGGER.error("Could not delete extra image: "+ fileName);
					}
				}
			});
		}catch (IOException e) {
			// TODO: handle exception
			LOGGER.error("Could not list directory: "+ dirPath);
		}
	}
	static void setExistingExtraImageNames(String[] imageIDs, String[] imageNames, Product product) {
		// TODO Auto-generated method stub
		if(imageIDs == null || imageIDs.length == 0) return;
		Set<ProductImage> images = new HashSet<>();
		for(int count = 0; count < imageIDs.length; count++) {
			Integer id = Integer.parseInt(imageIDs[count]);
			String name = imageNames[count];
			images.add(new ProductImage(id, name, product));
		}
	}
	static void setProductDetails(String[] detailIDs,String[] detailNames, String[] detailValues, Product product) {
		if(detailNames == null || detailNames.length == 0) return;
		for(int count = 0; count < detailNames.length; count++ ) {
			String name = detailNames[count];
			String value = detailValues[count];
			Integer id = Integer.parseInt(detailIDs[count]);
			
			if(id != 0) {
				product.addDetail(id, name, value);
			}
			else if(!name.isEmpty() && !value.isEmpty()) {
				product.addDetail(name, value);
			}
		}
		
	}
	static void savedUploadedImage(MultipartFile mainImageMultipart, MultipartFile[] extraMultipartFiles,
			Product savedProduct) throws IOException {
		// TODO Auto-generated method stub
		if(!mainImageMultipart.isEmpty()) {
			String fileName = StringUtils.cleanPath(mainImageMultipart.getOriginalFilename());
			
			String uploadDirString = "../product-images/" + savedProduct.getId();
			
			FileUploadUtil.cleanDir(uploadDirString);
			FileUploadUtil.saveFile(uploadDirString, fileName,  mainImageMultipart);
		}
		if(extraMultipartFiles.length > 0) {
			String uploadDirString = "../product-images/" + savedProduct.getId() + "/extras";
			for(MultipartFile multipartFile : extraMultipartFiles) {
				if(multipartFile.isEmpty()) {
					continue;
				}
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				FileUploadUtil.saveFile(uploadDirString, fileName,  multipartFile);
			}
		}
	}
	static void setNewExtraImageNames(MultipartFile[] extraMultipartFiles, Product product) {
		if(extraMultipartFiles.length > 0) {
			for(MultipartFile multipartFile : extraMultipartFiles) {
				if(!multipartFile.isEmpty()) {
					String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
					if(!product.containsImageName(fileName)) {						
						product.addExtraImage(fileName);
					}
				}
			}
		}
	}
	static void setMainImageName(MultipartFile mainImageMultipart, Product product) {
		if(!mainImageMultipart.isEmpty()) {
			String fileName = StringUtils.cleanPath(mainImageMultipart.getOriginalFilename());
			product.setMainImage(fileName);
		}
	}
}
