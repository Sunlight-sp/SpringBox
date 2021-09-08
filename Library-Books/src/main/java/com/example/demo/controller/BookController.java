package com.example.demo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.entity.Library;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryRepository;

@RestController
public class BookController {
	
//	private RedisTemplate<String, Book> redisTemplate;
//	private HashOperations hashOperations;
//	
//	public BookController(RedisTemplate<String, Book> redisTemplate) {
//		super();
//		this.redisTemplate = redisTemplate;
//		hashOperations = redisTemplate.opsForHash();
//		
//	}

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@RequestMapping("/libraries/{libraryId}/books")
	public List<Book> getAllBooks(@PathVariable long libraryId) {
		return bookRepository.findByLibraryId(libraryId);
	}
	
	@Cacheable(value = "book", key = "#id")
	@RequestMapping("/libraries/{libraryId}/books/{id}")
	public Optional<Book> getBook(@PathVariable(value="libraryId") long libraryId, @PathVariable(value="id") long id) {
		System.out.println("book fetching from database:: "+id);
		return bookRepository.findByIdAndLibraryId(id, libraryId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/libraries/{libraryId}/books")
	public Book addBook(@RequestBody Book book, @PathVariable(value="libraryId") long libraryId) {
		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);
		Library library = optionalLibrary.get();
		book.setLibrary(library);
//		hashOperations.put("book", book.getId(), book);
		return bookRepository.save(book);

	}
	
	@CachePut(value = "book",key = "#id")
	@RequestMapping(method = RequestMethod.PUT, value="/libraries/{libraryId}/books/{id}")
	public Book updateBook(@PathVariable(value="libraryId") long libraryId, @PathVariable(value="id") long id, @RequestBody Book bookDetail) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		Book book = optionalBook.get();
		
		book.setName(bookDetail.getName());
		book.setAuthor(bookDetail.getAuthor());
		return bookRepository.save(book);
	}
	
	
	@CacheEvict(value = "book", allEntries = true)
	@RequestMapping(method = RequestMethod.DELETE, value="/libraries/{libraryId}/books/{id}")
	public Map<String, Boolean> deleteLibrary(@PathVariable(value="libraryId") long libraryId, @PathVariable(value="id") long id) {
		Book book = bookRepository.findById(id).get();

		bookRepository.delete(book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
