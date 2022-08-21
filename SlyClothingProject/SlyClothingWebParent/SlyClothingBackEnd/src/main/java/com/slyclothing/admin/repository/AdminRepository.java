package com.slyclothing.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.slyclothing.common.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
	@Query("SELECT a FROM Admin a WHERE a.email = :email")
	public Admin getAdminByEmail(@Param("email") String email);
	@Query("SELECT a FROM Admin a WHERE a.firstName = :firstName")
	public Admin getAdminByFirstName(@Param("firstName") String firstName);
	@Query("UPDATE Admin a SET a.enabled = ?2 where a.id = ?1")
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled);
	@Query("SELECT a FROM Admin a WHERE CONCAT(a.id, ' ', a.email, ' ', a.firstName, ' ',"+ "a.lastName) LIKE %?1%")
	public Page<Admin> fillAll(String keyword, Pageable pageable);
}
