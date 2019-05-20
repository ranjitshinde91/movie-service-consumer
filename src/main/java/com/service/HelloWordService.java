package com.service;

import com.model.Movie;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class HelloWordService {

    private RestTemplate restTemplate;

    public HelloWordService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String message(){
        String url = "http://localhost:8080/movies/DDLJ";
        ResponseEntity<Movie> response = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                Movie.class);

        return response.getBody().getName();
    }
}
