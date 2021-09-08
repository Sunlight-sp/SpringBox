package com.example.demo.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="registration")
public class Registration {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RegId")
	private long id;
	
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name="RegDate")
	private LocalDate registrationDate;
	
	
    @ManyToOne
    @JoinColumn(name = "SubjectId")
	private Subject subject;
	
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "StudentId")
	private Student student;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}



	public LocalDate getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}



	
    
    
}
