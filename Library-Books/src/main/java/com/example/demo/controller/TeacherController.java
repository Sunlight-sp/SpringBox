package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.MessagingConfig;
import com.example.demo.entity.Teacher;
import com.example.demo.publisher.TeacherPublisher;
import com.example.demo.repository.TeacherJpaRepository;

@RestController
public class TeacherController {
	
	@Autowired
	private TeacherJpaRepository teacherJpaRepository;
	
    @Autowired
    private RabbitTemplate template;
	
	@RequestMapping("/teachers")
	public List<Teacher> getAllTeacher() {
		return teacherJpaRepository.findAll();
	}
	
	@RequestMapping("/teachers/{id}")
	public Optional<Teacher> getTeacher(@PathVariable long id) {
		return teacherJpaRepository.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/teachers")
	public Teacher saveTeacher(@RequestBody Teacher teacherDetail) {
		//TeacherPublisher.publish(teacher);
		Teacher teacher = teacherJpaRepository.save(teacherDetail);
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, teacher);
		return teacher;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/teachers/{id}")
	public Teacher updateTeacher(@RequestBody Teacher teacherDetail, @PathVariable long id) {
		Teacher teacher = teacherJpaRepository.findById(id).get();
		teacher.setName(teacherDetail.getName());
		return teacherJpaRepository.save(teacher);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/teachers/{id}")
	public Map<String, Boolean> deleteTeacher(@PathVariable long id) {
		Teacher teacher = teacherJpaRepository.findById(id).get();
		teacherJpaRepository.delete(teacher);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
}
