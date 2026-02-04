package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {
	
	public Employee mapRow(ResultSet rs,int rowNum) throws SQLException{
		Employee emp=new Employee();
		emp.setId(rs.getInt("id"));
		emp.setName(rs.getString("name"));
		emp.setSalary(rs.getInt("salary"));
		return emp;
		}
	

}
