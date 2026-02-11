package com.example.demo.service;


import com.example.demo.entity.Department;
import com.example.demo.repo.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepo;

    public DepartmentService(DepartmentRepository departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    // 1) LAZY behavior demo (employees will load when accessed inside transaction)
    @Transactional(readOnly = true)
    public Department getDepartmentLazy(Long id) {
        Department d = departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found: " + id));

        // Touch employees -> triggers SQL for employees if LAZY
        d.getEmployees().size();
        return d;
    }

    // 2) N+1 fix using JOIN FETCH
    @Transactional(readOnly = true)
    public List<Department> getAllWithEmployeesJoinFetch() {
        return departmentRepo.findAllWithEmployeesJoinFetch();
    }

    // 3) Using EntityGraph (employees fetched for this call)
    @Transactional(readOnly = true)
    public List<Department> getAllWithEmployeesEntityGraph() {
        return departmentRepo.findAll();
    }
}