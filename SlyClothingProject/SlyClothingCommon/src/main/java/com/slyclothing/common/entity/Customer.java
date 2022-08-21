package com.slyclothing.common.entity;






import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, length = 64)
	private String password;

	@Column(name = "name", nullable = false, length = 45)
	private String name;
	@Column(name = "phone_number", nullable = false, length = 15)
	private String phoneNumber;
	private boolean enabled;
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Customer(Integer id, String email, String password, String name, String phoneNumber, Country country) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.country = country;
	}
	public Customer() {
	
	}
	
	public Customer(Integer id) {
		
		this.id = id;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", phoneNumber=" + phoneNumber + ", country=" + country + "]";
	}
	
	
	
	
}
