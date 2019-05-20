package com;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.*;
import au.com.dius.pact.model.RequestResponsePact;
import au.com.dius.pact.model.matchingrules.RegexMatcher;
import au.com.dius.pact.model.matchingrules.TypeMatcher;
import com.google.common.collect.ImmutableMap;
import com.service.HelloWordService;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Map;
import java.util.TimeZone;


public class GetMovieContractTest {

    private HelloWordService helloWordService =
            new HelloWordService(new RestTemplate());

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("MovieService", "localhost", 8080, this );


    @Pact(consumer = "movie-consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder){
        Map<String, String> headers = ImmutableMap.of("Content-Type", "application/json");

        PactDslJsonBody dsl = new PactDslJsonBody() ;
        dsl.stringType("name", "DDLJ");
        dsl.integerType("length");
        dsl.date("releaseDate", "yyyy-MM-dd");


        return builder.given("Test Get")
                .uponReceiving("GET REQUEST")
                .path("/movies/DDLJ")
                .method("GET")
                .willRespondWith()
                .status(HttpStatus.SC_OK)
                .headers(headers)
                .body(dsl)
                .toPact();



    }

    @Test
    @PactVerification
    public void verify(){
        String message = helloWordService.message();
    }

}
