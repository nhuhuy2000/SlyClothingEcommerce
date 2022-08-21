package com.slyclothing.admin.state;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slyclothing.common.entity.Country;
import com.slyclothing.common.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
	public List<State> findByCountryOrderByNameAsc(Country country);
}
