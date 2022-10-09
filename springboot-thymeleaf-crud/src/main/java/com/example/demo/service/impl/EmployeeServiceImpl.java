package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public List<Employee> getAllEmployee() {
		return employeeDao.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	public Employee getEmployeeById(int id) {
		Optional<Employee> optional = employeeDao.findById(id);
		Employee employee=null;
		if(optional.isPresent()) {
			employee = optional.get();
		}
		else {
			throw new RuntimeException("employee not find for id: "+id);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(int id) {
		employeeDao.deleteById(id);
	}

}
