package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Subject;

public interface SubjectRepository extends JpaRepository <Subject, Long>{
//	public List<Subject> findBySchoolId(long schoolId);

}
