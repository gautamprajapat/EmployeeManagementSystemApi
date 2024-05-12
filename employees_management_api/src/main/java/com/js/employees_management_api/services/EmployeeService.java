package com.js.employees_management_api.services;

import java.util.List;

import com.js.employees_management_api.model.Employee;

public interface EmployeeService  {

	Employee createEmployee(Employee employee);

	

	List<Employee> getAllEmployee();

	boolean deleteEmployee(Long id);



	Employee getEmployeeId( Long id);



	Employee updateEmployee(Long id, Employee employee);
	

}
