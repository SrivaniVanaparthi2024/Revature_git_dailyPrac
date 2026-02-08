package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.repo.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
//	private final EmployeeRepository employeeRepo;
//
//	public EmployeeService(EmployeeRepository employeeRepo) {
//		this.employeeRepo = employeeRepo;
//	}
//
//	// Create employee + profile in one go
//	public Employee createEmployeeWithProfile(String name, String email, String phone, String address) {
//		Employee emp = new Employee(name, email);
//		EmployeeProfile profile = new EmployeeProfile(phone, address);
//
//		emp.setProfile(profile); // helper sets both sides
//		return employeeRepo.save(emp); // cascade saves profile also
//	}
//
//	public List<Employee> getAll() {
//		return employeeRepo.findAll();
//	}
//
//	public Employee getById(Long id) {
//		return employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found: " + id));
//	}
//
//	public void deleteById(Long id) {
//		employeeRepo.deleteById(id); // orphanRemoval will delete profile automatically
//	}
	
	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee createEmoployeeWithProfile(String name, String email, String phone, String address) {
		Employee employee = new Employee(name, email);
		EmployeeProfile profile = new EmployeeProfile(phone, address);

		employee.setProfile(profile);
		return employeeRepository.save(employee);
	}

	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	public Employee getById(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found : " + id));
	}
	
	public void deleteById(Long id)
	{
		employeeRepository.deleteById(id);
	}
	  
    @Transactional
    public Employee updateEmployeeWithProfile(Long id, String name, String email, String phone, String address) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found : " + id));

       
        employee.setName(name);
        employee.setEmail(email);

        // update / create profile
        EmployeeProfile profile = employee.getProfile();
        if (profile == null) {
            profile = new EmployeeProfile();
            employee.setProfile(profile);
        }

        profile.setPhone(phone);
        profile.setAddress(address);

        // because employee is managed (@Transactional), changes are saved automatically
        return employeeRepository.save(employee);
    }

}
