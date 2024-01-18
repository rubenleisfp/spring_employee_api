package com.castelaofp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.castelaofp.model.Employee;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

	@Query ("select e from Employee e where e.name like %?1%")
	List<Employee> findByName(String name);}
