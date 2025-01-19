package com.fp.service;

import java.util.List;
import java.util.Optional;

import com.fp.dto.EmployeeDto;
import com.fp.model.Employee;

public interface EmployeeService {
	

	List<EmployeeDto> findAll();
	EmployeeDto create(EmployeeDto employee);
	Optional<EmployeeDto> update(Long employeeId, EmployeeDto employee);
	boolean delete(Long id);
	List<EmployeeDto> findByName(String name);
	Optional<EmployeeDto> getById(Long id);


}
