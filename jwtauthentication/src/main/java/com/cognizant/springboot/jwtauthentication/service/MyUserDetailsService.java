package com.cognizant.springboot.jwtauthentication.service;

import com.cognizant.springboot.jwtauthentication.model.User;
import com.cognizant.springboot.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * Class MyUserDetailsService
 *
 * Used to provide Customer UserDetails service
 *
 *
 * @author 841771 jaydatt
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (StringUtils.hasText(username)) {
            User user = userRepository.findById(username).orElse(null);
            if (user != null) {
                return new org.springframework.security.core.userdetails
                        .User(user.getUserName(), user.getPassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User not Found..!!");
            }
        }
        return null;
    }
}
