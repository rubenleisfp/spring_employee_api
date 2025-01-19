package com.fp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fp.model.Employee;


@Repository
@Qualifier("jpa")
public interface EmployeeJpaRepository   extends JpaRepository<Employee, Long>, EmployeeRepository  {

	@Query ("select e from Employee e where e.name like %?1%")
	List<Employee> findByName(String name);}
