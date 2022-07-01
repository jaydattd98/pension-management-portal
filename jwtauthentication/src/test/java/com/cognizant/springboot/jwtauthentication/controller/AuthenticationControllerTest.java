package com.cognizant.springboot.jwtauthentication.controller;

import com.cognizant.springboot.jwtauthentication.helper.JwtUtil;
import com.cognizant.springboot.jwtauthentication.model.JwtRequest;
import com.cognizant.springboot.jwtauthentication.repository.UserRepository;
import com.cognizant.springboot.jwtauthentication.service.MyUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void generateTokenTest() throws Exception {

        // Preparation
        JwtRequest jwtRequest = new JwtRequest("jaydatt", "jaydatt@123");
        String token = "bdkbkdb.jhdbfhkbdf.khdbfhkbckdhbkhbdkbfkjsbckbkjsbndk";
        //Mocking service response
        when(jwtUtil.generateToken(Mockito.any(UserDetails.class)))
                .thenReturn(token);
        when(myUserDetailsService.loadUserByUsername(Mockito.anyString()))
                .thenReturn(new User("Jaydatt", "jaydatt@123", new ArrayList<>()));

        when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(null);

        //Action
        mvc
                .perform(MockMvcRequestBuilders
                        .post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jwtRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token")
                        .value(token));

    }

    @Test
    public void isTokenValid() throws Exception {

        //Action
        mvc
                .perform(MockMvcRequestBuilders
                        .get("/isTokenValid"))
                .andExpect(status().isOk());
    }
}