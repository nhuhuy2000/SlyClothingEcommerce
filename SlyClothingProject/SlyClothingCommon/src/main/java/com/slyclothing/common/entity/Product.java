package com.slyclothing.common.entity;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, length = 256, nullable = false)
	private String name;
	@Column(unique = true, length = 256, nullable = false)
	private String alias;
	@Column(length = 512, nullable = false, name="short_description")
	private String shortDescription;
	@Column(length = 4096, nullable = false, name="full_description")
	private String fullDescription;
	private boolean enabled;
	public boolean isEnabled() {
		return enabled;
	}
	public List<ProductDetail> getDetails() {
		return details;
	}
	public void setDetails(List<ProductDetail> details) {
		this.details = details;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Column(name = "created_time")
	private Date createdTime;
	@Column(name = "updated_time")
	private Date updatedTime;
	@Column(name ="in_stock")

	private boolean inStock;
	
	private float cost;
	
	private float price;
	
	@Column(name = "main_image", nullable = false)
	private String mainImage;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductImage> images = new HashSet<>();
	
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public boolean isInStock() {
		return inStock;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Product() {
		super();
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Set<ProductImage> getImages() {
		return images;
	}
	public void setImages(Set<ProductImage> images) {
		this.images = images;
	}
	public Product(Integer id, String name, String alias, String shortDescription, String fullDescription,
			boolean enabled, Date createdTime, Date updatedTime, boolean inStock, float cost, float price,
			Category category, Brand brand) {
		super();
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.shortDescription = shortDescription;
		this.fullDescription = fullDescription;
		this.enabled = enabled;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.inStock = inStock;
		this.cost = cost;
		this.price = price;
		this.category = category;
		this.brand = brand;
	}
	public void addExtraImage(String imageName) {
		this.images.add(new ProductImage(imageName,this));
	}
	public void addDetail(String name, String value) {
		this.details.add(new ProductDetail(name, value, this));
	}
	public void addDetail(Integer id,String name, String value) {
		this.details.add(new ProductDetail(id ,name, value, this));
	}
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductDetail> details = new ArrayList<>();
	@Transient
	public String getMainImagePath() {
		if(id == null || mainImage == null) return "/images/thumbnail.png";
		return "/product-images/" + this.id + "/" + this.mainImage;
	}
	public boolean containsImageName(String imageName) {
		// TODO Auto-generated method stub
		Iterator<ProductImage> iterator = images.iterator();
		while (iterator.hasNext()) {
			ProductImage image = iterator.next();
			if(image.getName().equals(imageName)) {
				return true;
			}
		}
		return false;
	}
	
	public String getShortName() {
		if(name.length() > 70) {
			return name.substring(0, 70).concat("..");
		}
		return name;
	}
	public Product(Integer id) {

		this.id = id;
	}
	
}
