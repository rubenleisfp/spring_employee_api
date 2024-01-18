package com.castelaofp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.castelaofp.dto.EmployeeDto;
import com.castelaofp.model.Employee;

import jakarta.validation.Valid;


/**
 * http://localhost:8080/v3/api-docs
 * http://localhost:8080/swagger-ui/index.html
 * 
 */


public class EmployeeController {



	public List<EmployeeDto> findAll() {
		throw new UnsupportedOperationException();
	}
	

	public ResponseEntity<EmployeeDto> getById(@PathVariable("id") Long employeeId) {
		throw new UnsupportedOperationException();

	}

	public List<Employee> findByName(@RequestParam(name = "name") String name) {
		throw new UnsupportedOperationException();
	}


	public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto employeeDto) {
		throw new UnsupportedOperationException();
	}

	
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody EmployeeDto employeeDto) {
		throw new UnsupportedOperationException();
	}


	public ResponseEntity<EmployeeDto> delete(@PathVariable("id") Long employeeId) {
		throw new UnsupportedOperationException();
	}
}
