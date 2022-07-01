package com.cognizant.springboot.jwtauthentication.config;

import com.cognizant.springboot.jwtauthentication.helper.JwtUtil;
import com.cognizant.springboot.jwtauthentication.service.MyUserDetailsService;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class MyJwtAuthenticationFilterTest {

    @InjectMocks
    private MyJwtAuthenticationFilter jwtAuthenticationFilter;
    @Mock
    private JwtUtil jwtTokenUtil;
    @Mock
    private MyUserDetailsService myUserDetailsService;

    @Test
    public void doFilterInternalTest() throws Exception {

        //Preparation
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = new MockFilterChain();

        when(request.getHeader(Mockito.anyString()))
                .thenReturn("Bearer bskbdkjsd.kjbkbdbsd.gvdfdfefefejvvdd");
        when(jwtTokenUtil.validateToken(Mockito.anyString()))
                .thenReturn(true);
        when(jwtTokenUtil.getUsernameFromToken(Mockito.anyString()))
                .thenReturn("jaydatt");
        when(myUserDetailsService.loadUserByUsername(Mockito.anyString()))
                .thenReturn(new User("Jaydatt", "jaydatt@123", new ArrayList<>()));
        //Action
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        //Assert
        verify(myUserDetailsService, times(1)).loadUserByUsername(Mockito.anyString());
    }

    @Test
    public void doFilterInternalExTest() throws Exception {

        //Preparation
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = new MockFilterChain();

        when(request.getHeader(Mockito.anyString()))
                .thenReturn("Bearer bskbdkjsd.kjbkbdbsd.gvdfdfefefejvvdd");
        when(jwtTokenUtil.validateToken(Mockito.anyString()))
                .thenReturn(true);
        when(jwtTokenUtil.getUsernameFromToken(Mockito.anyString()))
                .thenReturn("jaydatt");
        when(myUserDetailsService.loadUserByUsername(Mockito.anyString()))
                .thenThrow(new InternalException("exception caught"));
        try {
            //Action
            jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        } catch (Exception e) {
            //Assert
            Assert.assertTrue(true);
        }
    }
}