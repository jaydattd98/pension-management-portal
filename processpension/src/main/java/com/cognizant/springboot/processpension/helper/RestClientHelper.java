package com.cognizant.springboot.processpension.helper;

import com.cognizant.springboot.processpension.entity.ProcessPensionInput;
import com.cognizant.springboot.processpension.services.SessionService;
import com.cognizant.springboot.processpension.services.impl.ProcessPensionServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Class RestClientHelper
 *
 * @author 841771 jaydatt
 */
@Service
public class RestClientHelper {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RestClientHelper.class);

    @Value("${rest.client.pensionerdetails}")
    private String pensionerDetailsClientUrl;

    @Autowired
    private SessionService sessionService;

    /**
     * Method will add token in header
     *
     * @return header
     */
    public HttpEntity<String> getHeader() {
        HttpHeaders headers = new HttpHeaders();
        String token = "Bearer " + sessionService.getToken();
        headers.add("Authorization", token);
        log.info("Setting Authorization inside Headers "+"Authorization = "+token);
        return new HttpEntity<String>(headers);
    }

    /**
     *Method will put aadhaarNumber in params
     *
     * @param processPensionInput
     * @return paramsMap
     */
    public Map getParams(ProcessPensionInput processPensionInput) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("aadhaarNumber", processPensionInput.getAadhaarNumber());
        return params;
    }

    /**
     * Method will create urlTemplate
     *
     * @param processPensionInput
     * @return urlTemplate
     */
    public String getUrlTemplate(ProcessPensionInput processPensionInput) {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(pensionerDetailsClientUrl)
                .queryParam("aadhaarNumber", processPensionInput.getAadhaarNumber())
                .encode()
                .toUriString();
        return urlTemplate;
    }
}
