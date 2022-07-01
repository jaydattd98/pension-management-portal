package com.cognizant.springboot.pensionerdetail.controller;

import com.cognizant.springboot.pensionerdetail.entity.PensionerDetail;
import com.cognizant.springboot.pensionerdetail.services.PensionerDetailService;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * Class PensionerDetailController
 *
 * @author 841771 jaydatt
 */
@CrossOrigin
@RestController
@RequestMapping("/pension-details/v1")
public class PensionerDetailController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PensionerDetailController.class);

    @Autowired
    private PensionerDetailService pensionerDetailService;

    /**
     * This method give the pension details
     *
     * @param aadhaarNumber
     * @return processPension
     * @throws Exception
     */
    @GetMapping("/PensionerDetailByAadhaar")
    public ResponseEntity<PensionerDetail> pensionerDetailByAadhaar(@RequestParam String aadhaarNumber) {

        String methodName = "PensionerDetailController#PensionerDetailByAadhaar";
        log.info("inside the " + methodName);
        PensionerDetail pensionerDetail = null;
        validateInput(aadhaarNumber);
        try {
            pensionerDetail = pensionerDetailService.getPensionerDetailByAadhaar(aadhaarNumber);
            if (pensionerDetail == null) {
                log.error("No Data found "+methodName);
                throw new NoSuchElementException("pensionerDetail is null from service");
            }
        } catch (InternalException e) {
            log.error("Exception occured in "+methodName + "Exception= "+e.getStackTrace());
            throw new InternalException(methodName);
        }
        log.info("End the "+methodName +" Got response ="+pensionerDetail.toString());
        return ResponseEntity.ok(pensionerDetail);
    }

    private void validateInput(String aadhaarNumber) {
        if (StringUtils.isEmpty(aadhaarNumber)) {
            log.error("AadhaarNumber is null");
            throw new NullPointerException("AadhaarNumber is null");
        }
    }
}
