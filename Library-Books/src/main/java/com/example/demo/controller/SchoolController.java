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


import com.example.demo.entity.City;
import com.example.demo.entity.School;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.SchoolRepository;

@RestController
public class SchoolController {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@RequestMapping("/cities/{cityId}/schools")
	public List<School> getAllSchools(@PathVariable long cityId) {
		return schoolRepository.findByCityId(cityId);
	}
	
	@RequestMapping("/cities/{cityId}/schools/{id}")
	public Optional<School> getSchool(@PathVariable(value="cityId") long cityId, @PathVariable(value="id") long id) {
		return schoolRepository.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/cities/{cityId}/schools")
	public School addSchool(@RequestBody School school, @PathVariable(value="cityId") long cityId) {
		Optional<City> optionalCity = cityRepository.findById(cityId);
		City city = optionalCity.get();
		school.setCity(city);
		return schoolRepository.save(school);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/cities/{cityId}/schools/{id}")
	public School updateSchool(@PathVariable(value="cityId") long cityId, @PathVariable(value="id") long id, @RequestBody School schoolDetail) {
		Optional<School> optionalSchool = schoolRepository.findById(id);
		School school = optionalSchool.get();
		
		school.setSchoolName(schoolDetail.getSchoolName());
		return schoolRepository.save(school);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/cities/{cityId}/schools/{id}")
	public Map<String, Boolean> deleteSchool(@PathVariable(value="cityId") long cityId, @PathVariable(value="id") long id) {
		School school = schoolRepository.findById(id).get();

		schoolRepository.delete(school);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
