package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployee();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(int id);
	void deleteEmployeeById(int id);
}
