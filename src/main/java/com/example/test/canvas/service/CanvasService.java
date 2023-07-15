package com.example.test.canvas.service;

import com.example.test.canvas.entity.Account;
import com.example.test.canvas.entity.BaseManager;
import com.example.test.canvas.entity.Course;
import com.example.test.canvas.entity.TokenResponse1;
import com.example.test.canvas.repository.AccountRepository;
import com.example.test.canvas.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class CanvasService {

    @Value("${canvas.api.base-url}")
    private String canvasApiBaseUrl;

    @Value("${canvas.api.key}")
    private String canvasApiKey;


    @Autowired
    private BaseManager baseManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CourseRepository courseRepository;

    private RestTemplate restTemplate;
    private String accessToken;

    public CanvasService() {
        this.restTemplate = new RestTemplate();
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void syncClassRosters(TokenResponse1 tokenResponse) throws IOException {



        // Construct the request with the OAuth access token
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",
                "Bearer " + tokenResponse.getAccessToken());
        RequestEntity<Void> requestEntity =
                new RequestEntity<>(headers, HttpMethod.GET, URI.create(canvasApiBaseUrl + "/api/v1/accounts"));

        // Make the API call using RestTemplate
        List<Account> accountsList =
                List.of(Objects.requireNonNull(restTemplate.exchange(requestEntity, Account[].class).getBody()));
        accountRepository.saveAll(accountsList);
        // Store the accounts in the database

        RequestEntity<Void> courseApiResponse =
                new RequestEntity<>(headers, HttpMethod.GET, URI.create(canvasApiBaseUrl + "/api/v1/courses"));
        List<Course> courseList =
                List.of(Objects.requireNonNull(restTemplate.exchange(requestEntity, Course[].class).getBody()));
        courseRepository.saveAll(courseList);
    }

    public void fetchAccessToken(String code) throws Exception {
        TokenResponse1 tokenResponse1 =
                baseManager.postTokenCall(code);
        System.out.println(tokenResponse1);
        syncClassRosters(tokenResponse1);
    }
}

