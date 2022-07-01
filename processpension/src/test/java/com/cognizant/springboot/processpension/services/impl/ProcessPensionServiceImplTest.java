package com.cognizant.springboot.processpension.services.impl;

import com.cognizant.springboot.processpension.configuration.RestTemplateClient;
import com.cognizant.springboot.processpension.entity.BankDetail;
import com.cognizant.springboot.processpension.entity.PensionDetail;
import com.cognizant.springboot.processpension.entity.PensionerDetail;
import com.cognizant.springboot.processpension.entity.ProcessPensionInput;
import com.cognizant.springboot.processpension.helper.ProcessPensionHelper;
import com.cognizant.springboot.processpension.helper.RestClientHelper;
import com.cognizant.springboot.processpension.services.SessionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProcessPensionServiceImplTest {

    @InjectMocks
    private ProcessPensionServiceImpl processPensionService;

    @Mock
    private RestTemplateClient restTemplateClient;
    @Mock
    private SessionService sessionService;
    @Mock
    private RestClientHelper restClientHelper;
    @Mock
    private ProcessPensionHelper processPensionHelper;

    @Test
    public void getProcessPensionTest() {
        //Preparation
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + "jwtToken");
        HttpEntity<String> requestHeader = new HttpEntity<String>(headers);

        PensionerDetail pensionDetails = new
                PensionerDetail(
                "Vaibhav",
                "DVSZ76IT",
                new Date(),
                100000,
                1000,
                true,
                new BankDetail("HDFC", "555555555", true));

        Map map = new HashMap();

        // Mocking
        when(restClientHelper.getUrlTemplate(Mockito.any()))
                .thenReturn("url");

        when(restClientHelper.getHeader())
                .thenReturn(requestHeader);
        when(restClientHelper.getParams(Mockito.any()))
                .thenReturn(map);

        when(restTemplateClient.exchange(
                "url",
                HttpMethod.GET,
                requestHeader,
                PensionerDetail.class,
                map))
                .thenReturn(ResponseEntity.ok().body(pensionDetails));


        //Action
        PensionDetail response = processPensionService.getProcessPension(new ProcessPensionInput("123456789"));

        //Assert
        Assert.assertNotNull(response);
    }


    @Test
    public void getProcessPensionExTest() {
        //Preparation
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + "jwtToken");
        HttpEntity<String> requestHeader = new HttpEntity<String>(headers);

        PensionerDetail pensionDetails = new
                PensionerDetail(
                "Vaibhav",
                "DVSZ76IT",
                new Date(),
                100000,
                1000,
                true,
                new BankDetail("HDFC", "555555555", true));

        Map map = new HashMap();

        // Mocking
        when(restClientHelper.getUrlTemplate(Mockito.any()))
                .thenReturn("url");

        when(restClientHelper.getHeader())
                .thenReturn(requestHeader);
        when(restClientHelper.getParams(Mockito.any()))
                .thenReturn(map);

        when(restTemplateClient.exchange(
                "url",
                HttpMethod.GET,
                requestHeader,
                PensionerDetail.class,
                map))
                .thenReturn(ResponseEntity.badRequest().body(""));

        try {
            //Action
            PensionDetail response = processPensionService.getProcessPension(new ProcessPensionInput("123456789"));
        } catch (RestClientException e) {
            //Assert
            Assert.assertTrue(true);
        }


    }
}