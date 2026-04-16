package com.jsp.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
