package com.castelaofp.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private BigDecimal salary;
	private Date createdDate;

	// Constructor, getters, and setters

	public Employee() {
		// Default constructor
	}


	public Employee(Long id, String name, BigDecimal salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.createdDate = new Date();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", createdDate=" + createdDate + "]";
	}
	
}