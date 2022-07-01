package com.cognizant.springboot.jwtauthentication;

import com.cognizant.springboot.jwtauthentication.model.User;
import com.cognizant.springboot.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("com.cognizant.springboot.jwtauthentication")
@SpringBootApplication
public class JwtauthenticationApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(JwtauthenticationApplication.class, args);
        System.out.println("Jwtauthentication Application has Started..!!");
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("jaydatt", "jaydatt@123", "9158224033"));
    }
}
