package com.castelaofp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.castelaofp.dto.EmployeeDto;
import com.castelaofp.mapper.EmployeeMapper;
import com.castelaofp.model.Employee;
import com.castelaofp.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


/**
 * http://localhost:8080/v3/api-docs
 * http://localhost:8080/swagger-ui/index.html
 * 
 */

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Operation(summary = "Get all employees")
	@GetMapping
	public List<EmployeeDto> findAll() {
		List<Employee> entities = employeeService.findAll();
		return EmployeeMapper.toDto(entities);
	}
	
	@Operation(summary = "Get an employee by its id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Employee found", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = Employee.class)) }),
	  @ApiResponse(responseCode = "404", description = "Employee not found", 
	    content = @Content) })
	@GetMapping(value = "/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable("id") Long employeeId) {

		Optional<Employee> employee = employeeService.getById(employeeId);
		if (employee.isPresent()) {
			EmployeeDto employeeDto = EmployeeMapper.toDto(employee.get());
			return ResponseEntity.ok().body(employeeDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Get all employees with %name%")
	@GetMapping(value = "search")
	public List<Employee> findByName(@RequestParam(name = "name") String name) {
		return employeeService.findByName(name);
	}

	@Operation(summary = "Create an employee")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Employee created", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = EmployeeDto.class)) }),
	  @ApiResponse(responseCode = "400", description = "Data not valid", 
	    content = @Content) })
	@PostMapping
	public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.toEntity(employeeDto);
		employee = employeeService.create(employee);
		EmployeeDto dtoWithId = EmployeeMapper.toDto(employee);
		return new ResponseEntity<>(dtoWithId, HttpStatus.CREATED);
	}

	@Operation(summary = "Update an employee by its id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Employee updated", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = EmployeeDto.class)) }),
	  @ApiResponse(responseCode = "404", description = "Employee not found", 
	    content = @Content),
	  @ApiResponse(responseCode = "400", description = "Data not valid", 
	    content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.toEntity(employeeDto);
		Optional<Employee> optionalEmployee = employeeService.update(employeeId, employee);
		if (optionalEmployee.isPresent()) {
			employeeDto = EmployeeMapper.toDto(employee);
			return ResponseEntity.ok(employeeDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Delete an employee by its id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Employee deleted", 
	    content = { @Content(mediaType = "application/json")}),
	  @ApiResponse(responseCode = "404", description = "Employee not found", 
	    content = @Content) })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<EmployeeDto> delete(@PathVariable("id") Long employeeId) {
		boolean deleted = employeeService.delete(employeeId);
		if (deleted) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
