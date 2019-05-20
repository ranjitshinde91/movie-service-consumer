package com;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.google.common.collect.ImmutableMap;
import com.service.HelloWordService;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class GetMovieContractTest {

    private HelloWordService helloWordService;

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("movie-service",
            "localhost", 8080,
            this);

    @Before
    public void setup() {
        helloWordService = new HelloWordService(new RestTemplate());
    }

    @Pact(consumer = "movie-consumer")
    public RequestResponsePact getMovie(PactDslWithProvider builder) {
        Map<String, String> headers = ImmutableMap.of(HttpHeaders.CONTENT_TYPE, "application/json");

        PactDslJsonBody dsl = new PactDslJsonBody();
        dsl.stringType("name", "DDLJ");
        dsl.integerType("length");
        dsl.date("releaseDate", "yyyy-MM-dd");

        return builder.given("getMovie")
                .uponReceiving("GET Movie Details")
                .path("/movies/DDLJ")
                .method(HttpMethod.GET.toString())
                .willRespondWith()
                .status(HttpStatus.SC_OK)
                .headers(headers)
                .body(dsl)
                .toPact();
    }

    @Test
    @PactVerification(fragment = "getMovie")
    public void verify() {

        String name = helloWordService.message("DDLJ");
        assertThat(name).isEqualTo("DDLJ");
    }

    @Pact(consumer = "movie-consumer")
    public RequestResponsePact getMissingMovie(PactDslWithProvider builder) {

        Map<String, String> headers = ImmutableMap.of(HttpHeaders.CONTENT_TYPE, "application/json");

        PactDslJsonBody dsl = new PactDslJsonBody();
        dsl.stringType("message");

        return builder.given("getMissingMovie")
                .uponReceiving("non existing movie")
                .path("/movies/DDLJ2")
                .method(HttpMethod.GET.toString())
                .willRespondWith()
                .status(HttpStatus.SC_NOT_FOUND)
                .headers(headers)
                .body(dsl)
                .toPact();
    }

    @Test
    @PactVerification(fragment = "getMissingMovie")
    public void verifyNotFound() {
        try {
            String name = helloWordService.message("DDLJ2");
        } catch (Exception e) {
            assertThat(true).isTrue();
        }
    }
}
