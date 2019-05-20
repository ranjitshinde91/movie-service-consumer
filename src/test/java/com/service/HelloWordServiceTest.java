package com.service;

import com.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class HelloWordServiceTest {

    private HelloWordService subject;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup(){
        initMocks(this);
        subject = new HelloWordService(restTemplate);
    }

    @Test
    void message() {

        ResponseEntity<Movie> response = new ResponseEntity<>(HttpStatus.OK);
        //when(restTemplate.exchange()

        assertEquals(1, 1);

    }
}