package com.slyclothing.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "color")
public class MauSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int mamau;
	String tenmau;
	int soluong;
	public int getMamau() {
		return mamau;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public void setMamau(int mamau) {
		this.mamau = mamau;
	}
	public String getTenmau() {
		return tenmau;
	}
	public void setTenmau(String tenmau) {
		this.tenmau = tenmau;
	}
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "colors_sizes" , joinColumns = @JoinColumn(name = "color_id"), inverseJoinColumns = @JoinColumn(name = "size_id"))
	private Set<SizeSanPham> sizes = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
