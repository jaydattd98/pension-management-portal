package com.cognizant.springboot.jwtauthentication.service;

import com.cognizant.springboot.jwtauthentication.model.User;
import com.cognizant.springboot.jwtauthentication.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MyUserDetailsServiceTest {

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void loadUserByUsernameTest() {
        // PREPARATION
        User user = new User("jaydatt", "jaydatt@123", "4352353314");
        // MOCKING
        when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
        // CALLING TO TEST METHOD
        UserDetails userDetails = myUserDetailsService.loadUserByUsername("jaydatt");
        // ASSERTION FOR EXPECTATION
        Assert.assertNotNull(userDetails);
    }

    @Test
    public void loadUserByUsernameExTest() {

        try {
            UserDetails userDetails = myUserDetailsService.loadUserByUsername("jaydatt224");
        } catch (UsernameNotFoundException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void loadUserByUsernameNullTest() {


        UserDetails userDetails = myUserDetailsService.loadUserByUsername("");

        Assert.assertNull(userDetails);
    }
}