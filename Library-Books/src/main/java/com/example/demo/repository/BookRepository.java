package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Book;

public interface BookRepository extends JpaRepository <Book,Long> {
	public List<Book> findByLibraryId(long libraryId);
	public Optional<Book> findByIdAndLibraryId(long id, long libraryId);
}
