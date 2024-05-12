package com.js.employees_management_api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.employees_management_api.entity.EmployeeEntity;
import com.js.employees_management_api.model.Employee;
import com.js.employees_management_api.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	 private EmployeeRepository employeeRepository;

	    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
	        this.employeeRepository = employeeRepository;
	    }

	@Override
	public Employee createEmployee(Employee employee) {
		EmployeeEntity employeeEntity=new EmployeeEntity();
		BeanUtils.copyProperties(employee, employeeEntity);
		employeeRepository.save(employeeEntity);
		return employee;
	}

	@Override
	public List<Employee>getAllEmployee(){
		List<EmployeeEntity>employeeEntities=employeeRepository.findAll();
		
		//convert employee entity into new object
		List<Employee> employees=employeeEntities
			    .stream()
				.map(emp-> new Employee(
						emp.getId(),
						emp.getFirstName(),
						emp.getLastName(),
						emp.getEmail())).collect(Collectors.toList());
				
		
		return employees;
	}

	@Override
	public boolean deleteEmployee(Long id) {
		 Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(id);
		    if (employeeOptional.isPresent()) {
		        EmployeeEntity employee = employeeOptional.get();
		        employeeRepository.delete(employee);
		        return true;
		    }
		    return false;

}

	@Override
	public Employee getEmployeeId( Long id) {
		EmployeeEntity employeeEntity=
				employeeRepository.findById(id).get();
		Employee employee=new Employee();
		BeanUtils.copyProperties(employeeEntity, employee);
		
		return employee;
	}

	 @Override
	    public Employee updateEmployee(Long id, Employee employee) {
	        EmployeeEntity employeeEntity
	                = employeeRepository.findById(id).get();
	        
	        employeeEntity.setFirstName(employee.getFirstName());
	        employeeEntity.setLastName(employee.getLastName());
	        employeeEntity.setEmail(employee.getEmail());

	        employeeRepository.save(employeeEntity);
	        return employee;
	    }
}
