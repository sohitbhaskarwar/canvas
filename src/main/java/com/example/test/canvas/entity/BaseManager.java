package com.example.test.canvas.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class BaseManager {

    @Value("${canvas.api.base-url}")
    private String canvasApiBaseUrl;

    @Value("${spring.security.oauth2.client.provider.canvas.token-uri}")
    private String canvasTokenUrl;

    @Value("${canvas.api.key}")
    private String canvasApiKey;

    @Value("${spring.security.oauth2.client.registration.canvas.client-id}")
    private String canvasClientId;

    @Value("${spring.security.oauth2.client.registration.canvas.client-secret}")
    private String canvasClientSecret;

    @Value("${spring.security.oauth2.client.registration.canvas.authorization-grant-type}")
    private String canvasGrantType;

    @Value("${spring.security.oauth2.client.registration.canvas.redirect-uri}")
    private String redirectUrl;

    @Autowired
    private ObjectMapper mapper;

    public TokenResponse1 postTokenCall(String code) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // Set request headers
        HttpHeaders headers = new HttpHeaders();

        // Set request body parameters
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", canvasGrantType);
        requestBody.add("client_id", canvasClientId);
        requestBody.add("client_secret", canvasClientSecret);
        requestBody.add("redirect_uri", redirectUrl);
        requestBody.add("code", code);
        ResponseEntity<String> responseEntity = null;
        // Create the HTTP entity with headers and body
        try {
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, (MultiValueMap<String, String>) headers);

            // Make the POST request
            responseEntity = restTemplate.postForEntity(canvasTokenUrl, requestEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String responseBody = responseEntity.getBody();
        TokenResponse1 response = mapper.readValue(responseBody.toString(), TokenResponse1.class);

        return response;
    }

}
