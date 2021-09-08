package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="City")
public class City {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CityId")
	private long id;
	
	@Column(name="CityName")
	private String cityName;
	
	@OneToMany(mappedBy="city", cascade = CascadeType.ALL)
	private Set<School> schools = new HashSet<>();

	public City() {
		super();
	}
	
	public City(String cityName, Set<School> schools) {
		super();
		this.cityName = cityName;
		this.schools = schools;
	}

	public long getCityId() {
		return id;
	}

	public void setCityId(long cityId) {
		this.id = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Set<School> getSchools() {
		return schools;
	}

	public void setSchools(Set<School> schools) {
		this.schools = schools;
	}
	
	
	
}
