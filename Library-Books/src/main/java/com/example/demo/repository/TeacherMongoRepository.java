package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository; 

import com.example.demo.entity.TeacherMongo;

public interface TeacherMongoRepository extends MongoRepository<TeacherMongo, Long>{

}
