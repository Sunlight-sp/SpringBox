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

import com.example.demo.entity.Library;
import com.example.demo.repository.LibraryRepository;

@RestController
public class LibraryController {
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@RequestMapping("/libraries")
	public List<Library> getAllLibraries() {
		return libraryRepository.findAll();
	}
	
	@RequestMapping("/libraries/{id}")
	public Optional<Library> getLibrary(@PathVariable long id) {
		return libraryRepository.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/libraries")
	public Library createLibrary(@RequestBody Library library) {
		return libraryRepository.save(library);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/libraries/{id}")
	public Library updateLibrary(@PathVariable long id, @RequestBody Library libraryDetails ) {
		Optional<Library> optionalLibrary = libraryRepository.findById(id);
		Library library = optionalLibrary.get();
		library.setName(libraryDetails.getName());
		return libraryRepository.save(library);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/libraries/{id}")
	public Map<String, Boolean> deleteLibrary(@PathVariable long id) {
		Library library = libraryRepository.findById(id).get();

		libraryRepository.delete(library);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
