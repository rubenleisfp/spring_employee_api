package com.castelaofp.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.castelaofp.model.Employee;

@Primary
@Repository
public interface EmployeeJpaRepository   extends JpaRepository<Employee, Long>, EmployeeRepository  {

	@Query ("select e from Employee e where e.name like %?1%")
	List<Employee> findByName(String name);}
