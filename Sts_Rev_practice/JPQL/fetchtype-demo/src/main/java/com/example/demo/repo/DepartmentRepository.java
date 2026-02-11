package com.example.demo.repo;


import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Fix N+1 by fetching employees in the same query (JOIN FETCH)
    @Query("select distinct d from Department d left join fetch d.employees")
    List<Department> findAllWithEmployeesJoinFetch();

    // Another way: EntityGraph
    @EntityGraph(attributePaths = "employees")
    List<Department> findAll();
}