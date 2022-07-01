package com.cognizant.springboot.processpension.configuration;

import com.cognizant.springboot.processpension.services.impl.AuthServiceImpl;
import com.cognizant.springboot.processpension.services.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class JwtAuthenticationFilterTest {

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Mock
    private AuthServiceImpl authServiceImpl;
    @Mock
    private SessionService sessionService;

    @Test
    public void doFilterInternal() throws ServletException, IOException {

        //Preparation
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = new MockFilterChain();

        when(request.getHeader(Mockito.anyString()))
                .thenReturn("Bearer bskbdkjsd.kjbkbdbsd.gvdfdfefefejvvdd");
        when(authServiceImpl.isTokenValid(Mockito.anyString()))
                .thenReturn(true);

        //Action
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        //Assert
        verify(authServiceImpl, times(1)).isTokenValid(Mockito.anyString());
    }
}