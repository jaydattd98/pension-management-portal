package com.cognizant.springboot.pensionerdetail.services.impl;

import com.cognizant.springboot.pensionerdetail.configuration.RestTemplateClient;
import com.cognizant.springboot.pensionerdetail.services.AuthService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.net.URI;

/**
 * Class AuthService
 *
 * @author 841771 jaydatt
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private RestTemplateClient restTemplateClient;

    @Value("${rest.client.auth}")
    private String jwtAuthUrl;

    /**
     * This method will validate the token with help of jwtAuthMicroService
     *
     * @param jwtToken
     * @return isTokenValid
     */
    public boolean isTokenValid(String jwtToken) {

        String methodName = "AuthService#isTokenValide";
        log.info("Inside the " + methodName);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken);

        HttpEntity<String> request = new HttpEntity<String>(headers);
        URI uri = null;
        try {
            uri = new URI(jwtAuthUrl);
            ResponseEntity responseEntity = restTemplateClient.exchange(uri, HttpMethod.GET, request, Boolean.class);
            if (responseEntity.getStatusCode().value() == 200) {
                return true;
            }
        } catch (Exception e) {
            log.error("Exception occurred in " + methodName + "Exception= " + e.getStackTrace());
            throw new RestClientException("Exception get from jwtAuthMicroService");
        }
        return false;
    }


}
