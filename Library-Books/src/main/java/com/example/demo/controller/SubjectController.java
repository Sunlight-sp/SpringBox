package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.School;
import com.example.demo.entity.Subject;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.SubjectRepository;

@RestController
public class SubjectController {

	@Autowired 
	private SubjectRepository subjectRepository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	
	@RequestMapping("/subjects")
	public List<Subject> getAllSubjects() {
//		return subjectRepository.findBySchoolId(schoolId);
		return subjectRepository.findAll();
	}
	
	@RequestMapping("/subjects/{id}")
	public Optional<Subject> getSubject(@PathVariable(value="id") long id) {
		return subjectRepository.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/subjects")
	public Subject addSubject(@RequestBody Subject subject) {
//		School school = schoolRepository.findById(schoolId).get();
//		subject.setSchool(school);
		return subjectRepository.save(subject);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/subjects/{id}")
	public Subject updateSubject(@RequestBody Subject subjectDetail, @PathVariable(value="id") long id) {
		Subject subject = subjectRepository.findById(id).get();
		subject.setName(subjectDetail.getName());
		return subjectRepository.save(subject);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/subjects/{id}")
	public Map<String, Boolean> Subject(@PathVariable(value="id") long id) {
		Subject subject = subjectRepository.findById(id).get();

		subjectRepository.delete(subject);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
