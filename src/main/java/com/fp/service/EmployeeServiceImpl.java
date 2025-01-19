package com.fp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fp.dto.EmployeeDto;
import com.fp.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fp.model.Employee;
import com.fp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	@Qualifier("jpa")
	private EmployeeRepository employeeRepository;


	@Override
	public List<EmployeeDto> findAll() {
		return EmployeeMapper.toDto(employeeRepository.findAll());
	}

	@Override
	public EmployeeDto create(EmployeeDto employeeDto) {
		employeeDto.setCreatedDate(new Date(System.currentTimeMillis()));
		Employee employee = EmployeeMapper.toEntity(employeeDto);
		Employee savedEmployeed = employeeRepository.save(employee);
		return EmployeeMapper.toDto(savedEmployeed);
	}

	@Override
	public Optional<EmployeeDto> update(Long id, EmployeeDto employeeDetails) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			// Actualizar los valores de la entidad
			employee.setName(employeeDetails.getName());
			employee.setSalary(employeeDetails.getSalary());
			// Guardar la entidad actualizada
			Employee updatedEmployee = employeeRepository.save(employee);

			// Convertir a DTO antes de devolver
			return Optional.of(EmployeeMapper.toDto(updatedEmployee));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public boolean delete(Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			employeeRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<EmployeeDto> findByName(String name) {
		return EmployeeMapper.toDto(employeeRepository.findByName(name));
	}

	@Override
	public Optional<EmployeeDto> getById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return Optional.of(EmployeeMapper.toDto(employee.get()));
		} else {
			return Optional.empty();
		}
	}

}