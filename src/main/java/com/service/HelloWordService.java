package com.service;

import com.model.Movie;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class HelloWordService {

    private static final String MOVIE_SERVICE_URL = "http://localhost:8080/movies/";

    private RestTemplate restTemplate;

    public HelloWordService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String message(String id){
        String url = MOVIE_SERVICE_URL;
        ResponseEntity<Movie> response = restTemplate.exchange(url+id,
                HttpMethod.GET,
                null,
                Movie.class);

        return response.getBody().getName();
    }
}
