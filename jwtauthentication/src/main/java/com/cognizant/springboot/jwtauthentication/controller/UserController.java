package com.cognizant.springboot.jwtauthentication.controller;

import com.cognizant.springboot.jwtauthentication.model.User;
import com.cognizant.springboot.jwtauthentication.repository.UserRepository;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Class UserController
 *
 * @author 841771 jaydatt
 */
@CrossOrigin
@RestController
@RequestMapping("/auth/v1")
public class UserController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Used to create user Account
     *
     * @param user
     * @return ResponseEntity<User>
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (validateUserDetails(user)) {
            try {
                User userCreated = userRepository.save(user);
                return new ResponseEntity<User>(user, HttpStatus.CREATED);
            } catch (Exception e) {
              log.error("Error creating User");
              throw new InternalException("Error creating User");
            }
        }
        return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param user
     * @return valid
     */
    private boolean validateUserDetails(User user) {
        return user != null && StringUtils.hasText(user.getUserName()) &&
                StringUtils.hasText(user.getPassword()) && StringUtils.hasText(user.getPhoneNumber());
    }
}
