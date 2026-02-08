package com.example.demo;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component  //Spring will manage this class as a bean
@RequestScope//creates new object for each http request
public class RequestBean {
    private String requestId;

    public RequestBean() {
        this.requestId = UUID.randomUUID().toString();
        System.out.println("RequestBean Created: " + requestId);
    }

    public String getRequestId() {
        return requestId;
    }

}
