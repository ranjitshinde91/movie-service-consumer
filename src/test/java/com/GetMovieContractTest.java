package com;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.google.common.collect.ImmutableMap;
import com.service.HelloWordService;
import org.apache.http.HttpStatus;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


public class GetMovieContractTest {

    private HelloWordService helloWordService =
            new HelloWordService(new RestTemplate());

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("movie-service",
            "localhost", 8080,
            this);


    @Pact(consumer = "movie-consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = ImmutableMap.of("Content-Type", "application/json");

        PactDslJsonBody dsl = new PactDslJsonBody();
        dsl.stringType("name", "DDLJ");
        dsl.integerType("length");
        dsl.date("releaseDate", "yyyy-MM-dd");


        return builder.given("getMovie")
                .uponReceiving("GET Movie Details")
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
    public void verify() {
        helloWordService.message();
    }

}
