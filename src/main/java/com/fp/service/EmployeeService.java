package com.fp.service;

import java.util.List;
import java.util.Optional;

import com.fp.model.Employee;

public interface EmployeeService {
	

	List<Employee> findAll();
	Employee create(Employee employee);
	Optional<Employee> update(Long employeeId, Employee employee);
	boolean delete(Long id);
	List<Employee> findByName(String name);
	Optional<Employee> getById(Long id);


}
