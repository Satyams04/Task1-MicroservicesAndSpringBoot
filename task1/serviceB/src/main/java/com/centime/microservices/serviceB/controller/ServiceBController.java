package com.centime.microservices.serviceB.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ServiceBController {
	
    @GetMapping("/hello")
    public ResponseEntity<String> printHello() {
        return new ResponseEntity<>("Hello",HttpStatus.OK);
    }
}
