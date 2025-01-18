package com.fp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fp.dto.EmployeeDto;

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
	

	public ResponseEntity<?> getById(@PathVariable("id") Long employeeId) {
		throw new UnsupportedOperationException();

	}

	public List<EmployeeDto> findByName(@RequestParam(name = "name") String name) {
		throw new UnsupportedOperationException();
	}


	public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto employeeDto) {
		throw new UnsupportedOperationException();
	}

	
	public ResponseEntity<?> update(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody EmployeeDto employeeDto) {
		throw new UnsupportedOperationException();
	}


	public ResponseEntity<?> delete(@PathVariable("id") Long employeeId) {
		throw new UnsupportedOperationException();
	}
}
