package com.example.demo;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope   // ✅ One object for the whole application

public class AppBean {
	private final String appId;

    public AppBean() {
        this.appId = UUID.randomUUID().toString();
        System.out.println("AppBean Created (Only once for whole app): " + appId);
    }

    public String getAppId() {
        return appId;
    }

}
