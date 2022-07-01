package com.cognizant.springboot.jwtauthentication.config;

import com.cognizant.springboot.jwtauthentication.service.MyUserDetailsService;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class MySecurityConfig
 *
 * @author 841771 jaydatt
 */
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MySecurityConfig.class);

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private MyJwtAuthenticationFilter myJwtAuthenticationFilter;

    /**
     * Method will apply check
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) {
        String methodName = "MySecurityConfig#configure";
        log.info("Inside the " + methodName);
        try {
            http
                    .csrf()
                    .disable()
                    .cors()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/auth/v1/login", "/auth/v1/create").permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        } catch (Exception e) {
            log.error("Exception occured in " + methodName + "Exception= " + e.getStackTrace());
            throw new InternalException(methodName);
        }

        //filter to validate the tokens with every request
        http
                .addFilterBefore(myJwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console","/favicon.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
