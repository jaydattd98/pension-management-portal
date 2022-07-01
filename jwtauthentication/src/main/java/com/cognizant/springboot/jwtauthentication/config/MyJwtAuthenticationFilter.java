package com.cognizant.springboot.jwtauthentication.config;

import com.cognizant.springboot.jwtauthentication.helper.JwtUtil;
import com.cognizant.springboot.jwtauthentication.service.MyUserDetailsService;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class MyJwtAuthenticationFilter
 *
 * @author 841771 jaydatt
 */
@Component
public class MyJwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MyJwtAuthenticationFilter.class);

    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     * This method will filter the request from extract the jwt token and validate it
     *
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String methodName = "MyJwtAuthenticationFilter#doFilterInternal";
        log.info("Inside the " + methodName);
        String jwtToken = extractJwtFromRequest(request);

        try {
            if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
               log.info("Cannot set the Security Context");
            }
        } catch (Exception e) {
            log.error("Exception occured in "+methodName + "Exception= "+e.getStackTrace());
            throw new InternalException(methodName);
        }
        log.info("End the " + methodName);
        chain.doFilter(request, response);
    }

    /**
     * Used to extract token from requests
     *
     * @param request
     * @return token
     */
    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
