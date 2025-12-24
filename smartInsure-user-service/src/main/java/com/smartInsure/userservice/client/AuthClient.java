package com.smartInsure.userservice.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@Component
public class AuthClient {
    private final RestTemplate restTemplate;

    public AuthClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public  boolean validateToken(String authHeader){
        System.out.println("calling auth srvc with header "+ authHeader );
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",authHeader);
        HttpEntity<Void> entity = new HttpEntity<>(null,headers);
        ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:8081/auth/validate",
                HttpMethod.POST,
                entity, Boolean.class);
        return Boolean.TRUE.equals(response.getBody());
    }
}
