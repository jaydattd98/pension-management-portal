package com.cognizant.springboot.processpension.services;

import com.cognizant.springboot.processpension.entity.SessionData;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class Session Service
 * Used to store and fetch the tocken from session
 * @author 841771 jaydatt
 */

@Service
public class SessionService {

    @Autowired
    private SessionData sessionData;

    /**
     * Used to store token into session
     * @param token
     */
    public void setToken(String token) {
        sessionData.setToken(token);
    }

    /**
     * Used to fetch the tocken from session
     *
     *  @return token
     */
    public String getToken() {
        return sessionData.getToken();
    }
}
