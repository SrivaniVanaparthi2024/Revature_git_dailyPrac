package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	private final EmployeeDao employeeDao;
	
	public EmployeeController(EmployeeDao employeeDao) {
		this.employeeDao=employeeDao;
	}
	
	@GetMapping("/employees")
	public List<Employee> getEmployee(){
		return employeeDao.getallEmployees();
	}
	
	//using namedparameter
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable int id){
		return employeeDao.getEmployeeById(id);
	}
	
	@PostMapping("/employee")
	public String addEmployee(@RequestBody Employee emp) {
		System.out.println("Post method received from Request Body");
		System.out.println(emp.getId()+"-"+emp.getName()+"-"+emp.getSalary());
		employeeDao.addEmployee(emp);
		return "Employee Added Successfully";
	}

}
