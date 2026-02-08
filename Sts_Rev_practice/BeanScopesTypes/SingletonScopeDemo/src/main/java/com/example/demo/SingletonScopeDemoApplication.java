package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.service.MessageService;

@SpringBootApplication
public class SingletonScopeDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
		SpringApplication.run(SingletonScopeDemoApplication.class, args);
		
		// Getting the same bean twice
        MessageService obj1 = context.getBean(MessageService.class);
        MessageService obj2 = context.getBean(MessageService.class);

        System.out.println(obj1.getMessage());

        // Checking whether both are same object
        System.out.println("obj1 hashCode: " + obj1.hashCode());
        System.out.println("obj2 hashCode: " + obj2.hashCode());

        if (obj1 == obj2) {
            System.out.println("SAME OBJECT => Singleton Scope Confirmed!");
        } else {
            System.out.println(" Different Objects => Not Singleton");
        }

	}

}
