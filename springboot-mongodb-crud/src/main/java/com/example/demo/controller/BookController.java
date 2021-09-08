package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@PostMapping("/books")
	public Book saveBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	@GetMapping("/books")
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/books/{id}")
	public Optional<Book> getBook(@PathVariable long id) {
		return bookRepository.findById(id);
	}
	
	@PutMapping("/books/{id}")
	public Book updateBook(@PathVariable long id, @RequestBody Book bookDetail) {
		Book book = bookRepository.findById(id).get();
		book.setBookName(bookDetail.getBookName());
		book.setAuthorName(bookDetail.getAuthorName());
		return bookRepository.save(book);
	}
	
	
	@DeleteMapping("/books/{id}")
	public Map<String,Boolean> deleteBook(@PathVariable long id) {
		Book book = bookRepository.findById(id).get();
		
		bookRepository.delete(book);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	

	
}
