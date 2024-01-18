package com.castelaofp.dto;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeDto {


    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Salary is mandatory")
    private BigDecimal salary;

    private Date createdDate;

    // Constructor, getters, and setters

    public EmployeeDto() {
        // Default constructor
    }

    
    public EmployeeDto(Long id,String name, BigDecimal salary, Date createdDate) {
    	this.id = id;
    	this.name = name;
    	this.salary = salary;
    	this.createdDate = createdDate;
    	
    }

    // Getters and Setters

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
}