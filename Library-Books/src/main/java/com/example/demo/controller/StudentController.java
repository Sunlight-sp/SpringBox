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
import com.example.demo.entity.Student;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired 
	private StudentRepository studentRepository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@RequestMapping("/schools/{schoolId}/students")
	public List<Student> getAllStudents(@PathVariable long schoolId) {
		return studentRepository.findBySchoolId(schoolId);
	}
	
	@RequestMapping("/schools/{schoolId}/students/{id}")
	public Optional<Student> getStudent(@PathVariable(value="schoolId") long schoolId, @PathVariable(value="id") long id) {
		return studentRepository.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/schools/{schoolId}/students")
	public Student addStudent(@RequestBody Student student, @PathVariable long schoolId) {
		School school = schoolRepository.findById(schoolId).get();
		student.setSchool(school);
		return studentRepository.save(student);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/schools/{schoolId}/students/{id}")
	public Student updateStudent(@RequestBody Student studentDetail, @PathVariable(value="schoolId") long schoolId, @PathVariable(value="id") long id) {
		Student student = studentRepository.findById(id).get();
		student.setName(studentDetail.getName());
		student.setMobile(studentDetail.getMobile());
		student.setBirthday(studentDetail.getBirthday());
		return studentRepository.save(student);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/schools/{schoolId}/students/{id}")
	public Map<String, Boolean> Student(@PathVariable(value="schoolId") long schoolId, @PathVariable(value="id") long id) {
		Student student= studentRepository.findById(id).get();

		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
