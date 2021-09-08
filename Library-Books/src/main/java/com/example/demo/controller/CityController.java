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
import com.example.demo.entity.Library;
import com.example.demo.repository.CityRepository;

@RestController
public class CityController {
	
	@Autowired
	private CityRepository cityRepository;
	
	@RequestMapping("/cities")
	public List<City> getAllCities() {
		return cityRepository.findAll();
	}
	
	@RequestMapping("/cities/{id}")
	public Optional<City> getCity(@PathVariable long id) {
		return cityRepository.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/cities")
	public City addCity(@RequestBody City city) {
		return cityRepository.save(city);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/cities/{id}")
	public City updateCity(@RequestBody City cityDetail, @PathVariable long id) {
		Optional<City> optionalCity = cityRepository.findById(id);
		City city = optionalCity.get();
		city.setCityName(cityDetail.getCityName());
		return cityRepository.save(city);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/cities/{id}")
	public Map<String, Boolean> deleteCity(@PathVariable long id) {
		City city = cityRepository.findById(id).get();

		cityRepository.delete(city);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
