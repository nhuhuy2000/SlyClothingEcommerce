package com.slyclothing.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slyclothing.common.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
