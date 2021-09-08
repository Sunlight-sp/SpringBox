package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Teacher;

public interface TeacherJpaRepository extends JpaRepository<Teacher, Long>{

}
