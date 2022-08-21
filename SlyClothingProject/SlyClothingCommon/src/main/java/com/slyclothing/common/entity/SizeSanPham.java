package com.slyclothing.common.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "size")
public class SizeSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int masize;
	String size;
	String loai;
	public int getMasize() {
		return masize;
	}
	public void setMasize(int masize) {
		this.masize = masize;
	}
	public String getSize() {
		return size;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@ManyToMany(mappedBy = "sizes")
	private Set<MauSanPham> colors;
}
