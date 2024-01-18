package com.castelaofp.mapper;

import java.util.ArrayList;
import java.util.List;

import com.castelaofp.dto.EmployeeDto;
import com.castelaofp.model.Employee;

/**
 * Clase para transformar clase del modelo a dto y viceversa
 */
public class EmployeeMapper {
	
	
	public static List<EmployeeDto> toDto(List<Employee> entities) {
		List<EmployeeDto> dtos = new ArrayList<EmployeeDto>();
		for (Employee entity : entities) {
			dtos.add(toDto(entity));
		}
		return dtos;
				
	}
	
	public static EmployeeDto toDto(Employee entity) {
		EmployeeDto dto = new EmployeeDto(entity.getId(), entity.getName(), entity.getSalary(), entity.getCreatedDate());
		return dto;
		
	}
	
	public static Employee toEntity(EmployeeDto dto) {
		Employee entity = new Employee(dto.getId(), dto.getName(), dto.getSalary());
		return entity;
	}

}
