package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.rep.EmployeeRepository;

@Service
public class EmployeeService {
private final EmployeeRepository repo;

public EmployeeService(EmployeeRepository repo) {
	this.repo=repo;
}

public Employee saveEmployee(Employee employee) {
	System.out.println(employee.getId() +"- "+employee.getName()+"- "+employee.getSalary());
	return repo.save(employee);
}

public List<Employee> getAllEmployees(){
	return repo.findAll();
}

public Employee getEmployeeById(int id) {
	return repo.findById(id)
			.orElseThrow(()-> new RuntimeException("Employee not found with id: "+id));
}

public Employee updateEmployee(int id,Employee updateEmployee) {
	Employee existingEmployee=repo.findById(id)
			.orElseThrow(()-> new RuntimeException("Employee not found with id: "+id));
	existingEmployee.setName(updateEmployee.getName());
	existingEmployee.setSalary(updateEmployee.getSalary());
	
	return repo.save(existingEmployee);
}

public void deleteEmployee(int id) {
	Employee existing=repo.findById(id)
			.orElseThrow(()-> new RuntimeException("Employee not found with id: "+id));
	 repo.delete(existing);
	}


}
