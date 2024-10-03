package com.centime.microservices.serviceA.service;

import org.springframework.stereotype.Component;

@Component
public class ServiceAService {
	
    public boolean checkService() {
        return true; // Service is UP
    }
}
