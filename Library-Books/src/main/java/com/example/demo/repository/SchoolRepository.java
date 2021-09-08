package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.School;

public interface SchoolRepository extends JpaRepository <School, Long>{

	public List<School> findByCityId(long cityId);
//	public List<School> findByCityId(long cityId);

}
