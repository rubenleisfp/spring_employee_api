package com.castelaofp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.castelaofp.model.Employee;
import com.castelaofp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	@Qualifier("jpa")
	private EmployeeRepository employeeRepository;
	

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee create(Employee employee) {
		employee.setCreatedDate(new Date(System.currentTimeMillis()));
		return employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> update(Long id, Employee employeeDetails) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			employee.setName(employeeDetails.getName());
			employee.setSalary(employeeDetails.getSalary());

			return Optional.of(employeeRepository.save(employee));
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
	public List<Employee> findByName(String name) {
		return employeeRepository.findByName(name);
	}

	@Override
	public Optional<Employee> getById(Long id) {
		return employeeRepository.findById(id);
	}

}
