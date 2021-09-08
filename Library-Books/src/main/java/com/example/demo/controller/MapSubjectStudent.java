package com.example.demo.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Registration;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.repository.RegistrationRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;

@RestController
public class MapSubjectStudent {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@RequestMapping("/map/students/{studentId}/subjects/{subjectId}")
	public Map<String, Boolean> mapSubject(@PathVariable(value="studentId") long studentId, @PathVariable(value="subjectId") long subjectId) {
		Student student = studentRepository.findById(studentId).get();
		Subject subject = subjectRepository.findById(subjectId).get();
		
		Registration registration = new Registration();
		
		registration.setStudent(student);
		registration.setSubject(subject);
		registration.setRegistrationDate(LocalDate.now());
		
		registrationRepository.save(registration);
		
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("mapped", Boolean.TRUE);
		return response;
	}
	
}
