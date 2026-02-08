package com.example.demo.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class MessageService {

    public MessageService() {
        System.out.println("MessageService Constructor Called: Bean Created");
    }

    public String getMessage() {
        return "Hello! I am Singleton Bean";
    }
}

