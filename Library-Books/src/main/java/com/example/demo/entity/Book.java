package com.example.demo.entity;


import java.io.Serializable;
import java.util.Optional; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Book")
public class Book implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BookId", nullable=false)
	private long id;
	
	@Column(name="bookName")
	private String name;
	
	@Column(name="Author")
	private String author;
	
	//@JsonIgnore
	//avoid infinite loop
	//@JsonBackReference
	@JsonIgnore
	@ManyToOne(optional=false,fetch = FetchType.LAZY)
	@JoinColumn(name="LibraryId", referencedColumnName="LibraryId")
	private Library library;
	
	public Book() {
		super();

	}
	
	public Book(String name, String author, Library library) {
		super();
		this.name = name;
		this.author = author;
		this.library = library;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	//Delete getLibrary to avoid infinite loop
	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

//	public void setLibrary(Optional<Library> findById) {
//		// TODO Auto-generated method stub
//		
//	}
	
	
}
