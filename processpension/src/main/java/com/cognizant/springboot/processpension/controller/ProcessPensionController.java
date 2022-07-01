package com.cognizant.springboot.processpension.controller;

import com.cognizant.springboot.processpension.entity.PensionDetail;
import com.cognizant.springboot.processpension.entity.ProcessPensionInput;
import com.cognizant.springboot.processpension.entity.SessionData;
import com.cognizant.springboot.processpension.services.AuthService;
import com.cognizant.springboot.processpension.services.ProcessPensionService;
import com.cognizant.springboot.processpension.services.SessionService;
import com.cognizant.springboot.processpension.services.impl.ProcessPensionServiceImpl;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Class ProcessPentionController
 *
 * @author 841771 jaydatt
 */
@CrossOrigin
@RestController
@RequestMapping("/process-pension/v1")
public class ProcessPensionController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ProcessPensionController.class);

    @Autowired
    private SessionService sessionService;
    @Autowired
    private AuthService authService;

    @Autowired
    private ProcessPensionService processPensionService;

    /**
     * This method give the pension details
     *
     * @param processPensionInput
     * @return processPension
     * @throws Exception
     */
    @PostMapping("/ProcessPension")
    public ResponseEntity<PensionDetail> ProcessPension(@RequestHeader("Authorization") String token, @RequestBody ProcessPensionInput processPensionInput) {

        if (StringUtils.hasText(token)) {
            boolean isValide = authService.isTokenValid(token);
            if (isValide) {
                sessionService.setToken(token);
                log.info("Token is valid and proceeding further with request");
            }
        } else {
            log.warn("Token not present");
            throw new AuthenticationCredentialsNotFoundException("Token not present");
        }
        String methodName = "ProcessPensionController#ProcessPension";
        log.info("Inside the " + methodName);
        validateInput(processPensionInput);
        PensionDetail processPension = null;
        try {
            processPension = processPensionService.getProcessPension(processPensionInput);
        } catch (InternalException e) {
            log.error("Exception occurred in "+methodName + "Exception= "+e.getStackTrace());
            throw new InternalException(methodName);
        }
        log.info("End the "+methodName +" Got response ="+processPension.toString());
        return ResponseEntity.ok(processPension);
    }

    /**
     * Method will validate the input
     *
     * @param processPensionInput
     */
    private void validateInput(ProcessPensionInput processPensionInput) {
        if (processPensionInput == null) {
            log.error("Provided processPensionInput is null");
            throw new NullPointerException();
        } else if (processPensionInput != null && StringUtils.isEmpty(processPensionInput.getAadhaarNumber())) {
            log.error("Provided aadharNumber is null or empty");
            throw new NullPointerException();
        } else {
            log.info("Provided processPensionInput is valid");
        }
    }
}
