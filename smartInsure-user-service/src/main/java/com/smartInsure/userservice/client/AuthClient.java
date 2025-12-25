package com.smartInsure.userservice.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AuthClient {
    private final RestTemplate restTemplate;

    public AuthClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public Map<String,Object> validateAndGetUser(String authHeader){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",authHeader);
        HttpEntity<Void> entity = new HttpEntity<>(null,headers);
        ResponseEntity<Map> response = restTemplate.exchange("http://localhost:8081/auth/validate",
                HttpMethod.POST,
                entity, Map.class);
        return response.getBody();
    }
}
