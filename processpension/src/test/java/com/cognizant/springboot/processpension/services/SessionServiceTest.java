package com.cognizant.springboot.processpension.services;

import com.cognizant.springboot.processpension.entity.SessionData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SessionServiceTest {

    @InjectMocks
    private SessionService sessionService;
    @Mock
    private SessionData sessionData;

    @Test
    public void setToken() {
        Mockito.doNothing().when(sessionData).setToken(Mockito.anyString());
        sessionService.setToken("jwttoken");
        Mockito.verify(sessionData, Mockito.times(1)).setToken(Mockito.anyString());
    }

    @Test
    public void getToken() {
        Mockito.when(sessionData.getToken()).thenReturn("JwtToken");
        String token = sessionService.getToken();
        Assert.assertNotNull(token);
    }
}