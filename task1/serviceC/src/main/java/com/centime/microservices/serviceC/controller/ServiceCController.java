package com.centime.microservices.serviceC.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centime.microservices.serviceC.exception.InvalidJsonException;
import com.centime.microservices.serviceC.model.UserDetails;

@RestController
@RequestMapping("/api/v1")
public class ServiceCController {

    @PostMapping("/process")
    public ResponseEntity<String> processData(@RequestBody UserDetails userDetails) {
        
        if (userDetails.getName() == null || userDetails.getName().isEmpty())
            throw new InvalidJsonException("Name is required");
        
        if (userDetails.getSurname() == null || userDetails.getSurname().isEmpty())
            throw new InvalidJsonException("Surname is required");
               
        String concatenatedResult = userDetails.getName() + " " + userDetails.getSurname();
        return new ResponseEntity<>(concatenatedResult,HttpStatus.CREATED);
    }
}
