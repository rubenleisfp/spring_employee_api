package com.fp.repository;

import java.util.List;
import java.util.Optional;

import com.fp.model.Employee;

public interface EmployeeRepository {

	List<Employee> findAll();
	Employee save(Employee employee);
	void deleteById(Long id);
	List<Employee> findByName(String name);
	Optional<Employee> findById(Long id);

}
