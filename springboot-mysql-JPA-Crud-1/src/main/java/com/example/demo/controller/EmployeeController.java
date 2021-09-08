package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	//get employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return this.employeeRepository.findAll();
	}
	
	// get employees by id
	@GetMapping("/employees/{id}")
	@Cacheable(value = "employees",key = "#employeeId")
	public Optional<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		System.out.println("Employee fetching from database:: "+employeeId);
		return employeeRepository.findById(employeeId);
	}
	
	// save employees
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	// update employees
	@PutMapping("/employees/{id}")
	@CachePut(value = "employees",key = "#employeeId")
	public Employee updateEmployee(@PathVariable(value = "id") Long employeeId, @RequestBody Employee employeeDetails) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		Employee employee = optionalEmployee.get();
				

		employee.setEmail(employeeDetails.getEmail());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		return employeeRepository.save(employee);
	}
	
	// delete employees
	@DeleteMapping("/employees/{id}")
	@CacheEvict(value = "employees", allEntries = true)
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).get();

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
