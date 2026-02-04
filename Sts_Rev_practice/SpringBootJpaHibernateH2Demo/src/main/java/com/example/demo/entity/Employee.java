package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Table(name="employee")
@Table
public class Employee {

	@Id
	private int id;
	private String name;
	private int salary;
	
	public Employee() {}
	
	public Employee(int id,String name,int salary) {
		this.id=id;
		this.name=name;
		this.salary=salary;
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
	public void setSalary(int salary) {
		this.salary=salary;
	}
	public int getSalary() {
		return salary;
	}
	
	@Override
	public String toString() {
		return "id : "+id+"name : "+name+"salary : "+salary;
	}
}
