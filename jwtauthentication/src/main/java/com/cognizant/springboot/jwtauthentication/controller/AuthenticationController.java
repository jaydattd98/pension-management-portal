package com.cognizant.springboot.jwtauthentication.controller;

import com.cognizant.springboot.jwtauthentication.helper.JwtUtil;
import com.cognizant.springboot.jwtauthentication.model.JwtRequest;
import com.cognizant.springboot.jwtauthentication.model.JwtResponse;
import com.cognizant.springboot.jwtauthentication.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * Class AuthenticationController
 * Used to provide the Auth service api
 *
 * @author 841771 jaydatt
 */
@CrossOrigin
@RestController
@RequestMapping("/auth/v1")
public class AuthenticationController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * API used to login user and create token for him
     *
     * @param jwtRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) {
        String methodName = "AuthenticationController#generateToken";
        log.info("inside the " + methodName);
        try {
            authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken
                            (jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            log.error("Exception occurred in " + methodName + "Exception= " + e.getStackTrace());
            throw new UsernameNotFoundException("Bad Credentials..!!");
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        log.info("End the " + methodName + " Got token =" + token);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * API used to validate user token given by him
     * if we get respose 200 OK from this API we can say used token is valid
     * As we are validating it in filter
     *
     * @return ResponseEntity<Boolean>
     */
    @GetMapping("/isTokenValid")
    public ResponseEntity<Boolean> isTokenValid() {
        String methodName = "AuthenticationController#isTokenValid";
        log.info("inside the " + methodName);
        log.info("Provided Token is Valid returning true");
        return ResponseEntity.ok(true);
    }
}
