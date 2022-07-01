package com.cognizant.springboot.processpension.helper;

import com.cognizant.springboot.processpension.entity.ProcessPensionInput;
import com.cognizant.springboot.processpension.services.SessionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

@RunWith(SpringRunner.class)
public class RestClientHelperTest {

    @InjectMocks
    private RestClientHelper restClientHelper;
    @Mock
    private SessionService sessionService;

    @Test
    public void getHeaderTest() {
        Mockito.when(sessionService.getToken()).thenReturn("jwtToken");
        HttpEntity<String> response = restClientHelper.getHeader();
        Assert.assertNotNull(response);
    }

    @Test
    public void getParams() {
        Map response = restClientHelper.getParams(new ProcessPensionInput("12345678"));
        Assert.assertNotNull(response);
    }

    @Test
    public void getUrlTemplate() {
        ReflectionTestUtils.setField(restClientHelper,
                "pensionerDetailsClientUrl",
                "http://localhost:8585/PensionerDetailByAadhaar");
        String response = restClientHelper.getUrlTemplate(new ProcessPensionInput("12345678"));
        Assert.assertNotNull(response);
    }
}