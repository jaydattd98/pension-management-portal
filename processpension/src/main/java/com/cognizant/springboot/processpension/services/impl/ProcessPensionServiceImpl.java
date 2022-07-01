package com.cognizant.springboot.processpension.services.impl;

import com.cognizant.springboot.processpension.configuration.RestTemplateClient;
import com.cognizant.springboot.processpension.entity.PensionDetail;
import com.cognizant.springboot.processpension.entity.PensionerDetail;
import com.cognizant.springboot.processpension.entity.ProcessPensionInput;
import com.cognizant.springboot.processpension.helper.ProcessPensionHelper;
import com.cognizant.springboot.processpension.helper.RestClientHelper;
import com.cognizant.springboot.processpension.services.ProcessPensionService;
import com.cognizant.springboot.processpension.services.SessionService;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestClientException;

import java.util.NoSuchElementException;

/**
 * ProcessPensionServiceImpl
 *
 * @author 841771 jaydatt
 */
@Service
public class ProcessPensionServiceImpl implements ProcessPensionService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ProcessPensionServiceImpl.class);

    @Autowired
    private SessionService sessionService;
    @Autowired
    private RestClientHelper restClientHelper;
    @Autowired
    private ProcessPensionHelper processPensionHelper;
    @Autowired
    private RestTemplateClient restTemplateClient;

    /**
     * This method will give pension details from other microServices
     *
     * @param processPensionInput
     * @return pensionDetail
     */
    public PensionDetail getProcessPension(ProcessPensionInput processPensionInput) {


        String methodName = "ProcessPensionServiceImpl#getProcessPension";
        log.info("Inside the " + methodName);

        PensionDetail pensionDetail = new PensionDetail();
        try {
            ResponseEntity responseEntity =
                    restTemplateClient.
                            exchange(restClientHelper.getUrlTemplate(processPensionInput),
                                    HttpMethod.GET,
                                    restClientHelper.getHeader(),
                                    PensionerDetail.class,
                                    restClientHelper.getParams(processPensionInput));
            populatePensionDetails(pensionDetail, responseEntity);
        } catch (RestClientException | InternalException e) {
            log.error("Exception occurred in " + methodName + "Exception= " + e.getStackTrace());
            throw new RestClientException(e.getMessage());
        }
        log.info("End the " + methodName + " Got response =" + pensionDetail.toString());
        return pensionDetail;
    }

    /**
     * This method will populate the Response
     *
     * @param pensionDetail
     * @param responseEntity
     */
    private void populatePensionDetails(PensionDetail pensionDetail, ResponseEntity responseEntity) {
        String methodName = "ProcessPensionServiceImpl#populatePensionDetails";
        log.info("Inside the " + methodName);
        if (responseEntity.getStatusCode().value() == HttpStatus.OK.value()) {
            PensionerDetail pensionerDetail = (PensionerDetail) responseEntity.getBody();
            if (pensionerDetail != null) {
                pensionDetail.setPensionAmount(processPensionHelper.calculatePensionAmount(pensionerDetail));
                pensionDetail.setBankServiceCharge(processPensionHelper.calculateBankServiceCharge(pensionerDetail));
            } else {
                log.error("No data found for requested input");
                throw new NoSuchElementException();
            }
        } else {
            log.error("Unable to get respose from RestService");
            throw new InternalException("Unable to get respose from RestService");
        }
        log.info("End the " + methodName);
    }


}
