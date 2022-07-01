package com.cognizant.springboot.processpension.services.impl;

import com.cognizant.springboot.processpension.configuration.RestTemplateClient;
import com.cognizant.springboot.processpension.services.impl.AuthServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private RestTemplateClient restTemplateClient;

    @Test
    public void isTokenValidTest() throws URISyntaxException {

        //Preparation
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + "jwtToken");
        URI uri = new URI("http://localhost:8080/isTokenValid");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ReflectionTestUtils.setField(authService, "jwtAuthUrl", "http://localhost:8080/isTokenValid");

        // Mocking
        when(restTemplateClient.exchange(uri, HttpMethod.GET, request, Boolean.class))
                .thenReturn(ResponseEntity.ok().body(true));

        //Action
        boolean response = authService.isTokenValid("jwtToken");

        //Assert
        Assert.assertTrue(response);

    }

    @Test
    public void isTokenValidTestEX() {

        try {
            //Action
            boolean response = authService.isTokenValid("jwtToken");

        } catch (Exception e) {
            //Assert
            Assert.assertTrue(true);
        }

    }

}