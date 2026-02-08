package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.service.EmployeeService;

@SpringBootApplication
public class PrototypeScopeDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
                SpringApplication.run(PrototypeScopeDemoApplication.class, args);

        // Getting bean 2 times
        EmployeeService obj1 = context.getBean(EmployeeService.class);
        EmployeeService obj2 = context.getBean(EmployeeService.class);

        System.out.println(obj1.getEmployeeInfo());

        // Print hashCode to verify different objects
        System.out.println("obj1 hashCode: " + obj1.hashCode());
        System.out.println("obj2 hashCode: " + obj2.hashCode());

        if (obj1 == obj2) {
            System.out.println(" SAME OBJECT => Not Prototype");
        } else {
            System.out.println(" DIFFERENT OBJECTS => Prototype Scope Confirmed!");
        }
	}

}
