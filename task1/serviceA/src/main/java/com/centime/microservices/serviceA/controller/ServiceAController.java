package com.centime.microservices.serviceA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.centime.microservices.serviceA.exception.InvalidJsonException;
import com.centime.microservices.serviceA.model.UserDetails;
import com.centime.microservices.serviceA.service.ServiceAService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/")
public class ServiceAController {

    @Value("${serviceB.url}")
    private String serviceBUrl;

    @Value("${serviceC.url}")
    private String serviceCUrl;

	@Autowired
    RestTemplate restTemplate;
	
    public ServiceAController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
    @Autowired
    ServiceAService serviceA;

    @Operation(summary = "Get status of service", 
            description = "check the health status of the service")
    @ApiResponses(value = {
     @ApiResponse(responseCode = "200", description = "Service is UP"),
     @ApiResponse(responseCode = "503", description = "Service is Unavailable")
    })
    @GetMapping("/checkStatus")
    public ResponseEntity<String> getHealthStatus() {
    	// Check Service A health
        if (serviceA.checkService()) {
            return new ResponseEntity<>("Up",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Service Unavailable",HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Operation(summary = "Concatenate Services ", 
            description = "concatenate the response of two services")
    @ApiResponses(value = {
     @ApiResponse(responseCode = "201", description = "Concatenated the two services"),
     @ApiResponse(responseCode = "400", description = "Invalid Json Passed")
    })
    @PostMapping("/concatenateServices")
    public ResponseEntity<String> concatenateResponses(@RequestBody UserDetails userDetails) {
    	
    	//API Versioning defined in URL Endpoint
    	String endpoint = "/api/v1";
        
    	// Method to perform validation check on user details send in a request body
    	performInvalidJsonException(userDetails);
        
        // Step 1: Call Service B (GET)
        ResponseEntity<String> responseFromServiceB = restTemplate.
        		getForEntity(serviceBUrl + endpoint +"/hello", String.class);
        String msgFromServiceB = responseFromServiceB.getBody();
        
        // Step 2: Call Service C (POST)
        ResponseEntity<String> responseFromServiceC = restTemplate.
        		postForEntity(serviceCUrl + endpoint + "/process", userDetails, String.class);
        String msgFromServiceC = responseFromServiceC.getBody();
        
        // Step 3: Concatenate the responses
        String concatenatedResponse = msgFromServiceB + " " + msgFromServiceC;
        String finalResponse = String.format("{\n\"%s\"\n}", concatenatedResponse);
        return new ResponseEntity<>(finalResponse,HttpStatus.CREATED);
    }
    
    private void performInvalidJsonException(UserDetails userDetails) {   	
        if (userDetails.getName() == null || userDetails.getName().isEmpty())
            throw new InvalidJsonException("Name is required.");
        
        if (userDetails.getSurname() == null || userDetails.getSurname().isEmpty())
            throw new InvalidJsonException("Surname is required.");
    }
}