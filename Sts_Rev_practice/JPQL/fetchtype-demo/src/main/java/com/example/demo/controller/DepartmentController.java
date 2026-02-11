package com.example.demo.controller;


import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    // LAZY demo for one department
    // GET /departments/1/lazy
    @GetMapping("/{id}/lazy")
    public Department getOneLazy(@PathVariable Long id) {
        return service.getDepartmentLazy(id);
    }

    // JOIN FETCH (best to avoid N+1)
    // GET /departments/join-fetch
    @GetMapping("/join-fetch")
    public List<Department> joinFetch() {
        return service.getAllWithEmployeesJoinFetch();
    }

    // EntityGraph fetch
    // GET /departments/entity-graph
    @GetMapping("/entity-graph")
    public List<Department> entityGraph() {
        return service.getAllWithEmployeesEntityGraph();
    }
}