package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.City;
import com.example.demo.entity.Library;
import com.example.demo.entity.School;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.repository.SchoolRepository;


@RestController
public class MapSchoolLibrary {
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	
	@RequestMapping("/map/school/{schoolId}/library/{libraryId}")
	public Map<String, Boolean> mapLibrary(@PathVariable(value="schoolId") long schoolId, @PathVariable(value="libraryId") long libraryId) {
		Optional<School> optionalSchool = schoolRepository.findById(schoolId);
		School school = optionalSchool.get();
		
		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);
		Library library = optionalLibrary.get();
		
		library.setSchool(school);
		libraryRepository.save(library);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("mapped", Boolean.TRUE);
		return response;
	}

}
