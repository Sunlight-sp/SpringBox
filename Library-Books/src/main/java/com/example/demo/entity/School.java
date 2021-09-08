package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="School")
public class School {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SchoolId")
	private long id;
	
	@Column(name="SchoolName")
	private String schoolName;
	
	@JsonIgnore
	@ManyToOne(optional=false,fetch = FetchType.LAZY)
	@JoinColumn(name="CityId", referencedColumnName="CityId")
	private City city;
	
	
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "school")
	private Library library;


    @OneToMany(mappedBy="school", cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();
    
	public School() {
		super();
	}
	
	public School(String schoolName, City city) {
		super();
		this.schoolName = schoolName;
		this.city = city;
	}

	public long getSchoolId() {
		return id;
	}

	public void setSchoolId(long schoolId) {
		this.id = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}


	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	

}
