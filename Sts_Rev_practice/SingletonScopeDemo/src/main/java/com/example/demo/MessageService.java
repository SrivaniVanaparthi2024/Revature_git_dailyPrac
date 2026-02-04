package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
public MessageService() {
	System.out.println("Message constructor called: Bean Created");
}
public String getMessage() {
	return "Hellow from msgService class:Singleton Bean";
}
}
