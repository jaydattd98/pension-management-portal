package com.cognizant.springboot.pensionerdetail.configuration;

import com.cognizant.springboot.pensionerdetail.services.AuthService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class JwtAuthenticationFilter
 *
 * @author 841771 jaydatt
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private AuthService authService;

    /**
     * This method will filter the request from extract the jwt token and validate it
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String methodName = "JwtAuthenticationFilter#doFilterInternal";
        log.info("Inside the " + methodName);
        String jwtToken = extractJwtFromRequest(request);
        if (StringUtils.hasText(jwtToken)) {
            boolean isValide = authService.isTokenValid(jwtToken);
            if (isValide) {
                log.info("Token is valid and proceeding further with request");
                filterChain.doFilter(request, response);
            }
        } else {
            log.warn("Token not present");
            throw new AuthenticationCredentialsNotFoundException("Token not present");
        }
        log.info("End the " + methodName);
    }

    /**
     * Method Extract token from request
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

