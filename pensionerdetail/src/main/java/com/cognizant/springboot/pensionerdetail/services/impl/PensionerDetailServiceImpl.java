package com.cognizant.springboot.pensionerdetail.services.impl;

import com.cognizant.springboot.pensionerdetail.entity.AadhaarCard;
import com.cognizant.springboot.pensionerdetail.entity.PensionerDetail;
import com.cognizant.springboot.pensionerdetail.repository.AadhaarCardRepository;
import com.cognizant.springboot.pensionerdetail.repository.BankDeatilsRepository;
import com.cognizant.springboot.pensionerdetail.repository.PensionerDetailsRespository;
import com.cognizant.springboot.pensionerdetail.services.PensionerDetailService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;

/**
 * Class PensionerDetailService
 * Used to perform operation related to PensionerDetail
 *
 * @author 841771 jaydatt
 */
@Service
public class PensionerDetailServiceImpl implements PensionerDetailService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PensionerDetailServiceImpl.class);

    @Autowired
    private PensionerDetailsRespository pensionerDetailsRespository;
    @Autowired
    private BankDeatilsRepository bankDeatilsRepository;
    @Autowired
    private AadhaarCardRepository aadhaarCardRepository;

    /**
     * Used to get PensionerDetail from DAO layer
     *
     * @param aadharNumber
     * @return PensionerDetail
     */
    public PensionerDetail getPensionerDetailByAadhaar(String aadharNumber) {
        PensionerDetail pensionerDetail = null;
        if (StringUtils.hasText(aadharNumber)) {
            AadhaarCard aadhaarCard = aadhaarCardRepository.findById(aadharNumber).orElse(null);
            if (aadhaarCard != null && StringUtils.hasText(aadhaarCard.getPanNumber())) {
                pensionerDetail = pensionerDetailsRespository.findById(aadhaarCard.getPanNumber()).orElse(null);
            } else {
                log.error("No details found in DB for Aadhar number = " + aadharNumber);
                throw new NoSuchElementException("No details found in DB for Aadhar number = " + aadharNumber);
            }
        } else {
            throw new NullPointerException("AadharNumber is null");
        }
        return pensionerDetail;
    }
}
