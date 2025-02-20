package com.fp.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fp.model.Employee;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	private static Logger LOG = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);

	private final List<Employee> employeeList = new ArrayList<>();

	public EmployeeRepositoryImpl() {
		// Agregar algunos empleados de ejemplo
		save(new Employee(1L, "John Doe", new BigDecimal("50000.00")));
		save(new Employee(2L, "Jane Smith", new BigDecimal("60000.00")));
	}

	// Método para guardar/actualizar un nuevo empleado
	@Override
	public Employee save(Employee employee) {
		Optional<Employee> existingEmployee = findById(employee.getId());

		if (existingEmployee.isPresent()) {
			// Si el empleado ya existe, actualizarlo
			Employee updatedEmployee = existingEmployee.get();
			updatedEmployee.setName(employee.getName());
			updatedEmployee.setSalary(employee.getSalary());
			LOG.info("Empleado actualizado: {}", updatedEmployee);
			return updatedEmployee;
		} else {
			if (employee.getId() == null)
			{
				employee.setId(getAutoId());
			}
			// Si el empleado no existe, guardarlo
			employeeList.add(employee);
			LOG.info("Nuevo empleado guardado: {}", employee);
			return employee;
		}
	}
	
	private Long getAutoId() {
		return employeeList.get(employeeList.size()-1).getId() + 1;
	}

	// Método para obtener todos los empleados
	@Override
	public List<Employee> findAll() {
		return employeeList;
	}

	@Override
	public List<Employee> findByName(String name) {
		List<Employee> result = new ArrayList<>();

		for (Employee employee : employeeList) {
			if (employee.getName().toLowerCase().contains(name.toLowerCase())) {
				result.add(employee);
			}
		}

		return result;
	}

	// Método para obtener un empleado por ID
	@Override
	public Optional<Employee> findById(Long id) {
		for (Employee employee : employeeList) {
			if (employee.getId().equals(id)) {
				return Optional.of(employee);
			}
		}
		return Optional.empty();
	}

	//Alternativa a findById sin Optional
	//TODO Eliminar este main una vez entendido el Optional
	public Employee findByIdSinOptional(Long id) {
		for (Employee employee : employeeList) {
			if (employee.getId().equals(id)) {
				return employee;
			}
		}
		return null;
	}


	// Método para eliminar un empleado por ID
	public void deleteById(Long id) {
		employeeList.removeIf(employee -> employee.getId().equals(id));
	}

	//TODO Eliminar este main una vez entendido el Optional
	public static void main(String[] args) {
		EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl();
		repository.save(new Employee(1L, "John Doe", new BigDecimal("50000.00")));
		repository.save(new Employee(2L, "Jane Smith", new BigDecimal("60000.00")));

		Optional<Employee> employeeOptional = repository.findById(3L);
		if (employeeOptional.isPresent()) {
			System.out.println(employeeOptional.get().getName());
		}

		Employee employee = repository.findByIdSinOptional(3L);
		System.out.println(employee.getName());
	}

}
