package com.cognizant.springboot.pensionerdetail.services;

import com.cognizant.springboot.pensionerdetail.entity.AadhaarCard;
import com.cognizant.springboot.pensionerdetail.entity.BankDetail;
import com.cognizant.springboot.pensionerdetail.entity.PensionerDetail;
import com.cognizant.springboot.pensionerdetail.repository.AadhaarCardRepository;
import com.cognizant.springboot.pensionerdetail.repository.BankDeatilsRepository;
import com.cognizant.springboot.pensionerdetail.repository.PensionerDetailsRespository;
import com.cognizant.springboot.pensionerdetail.services.impl.PensionerDetailServiceImpl;
import com.cognizant.springboot.pensionerdetail.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PensionerDetailServiceImplTest {

    @InjectMocks
    private PensionerDetailServiceImpl pensionerDetailService;
    @Mock
    private PensionerDetailsRespository pensionerDetailsRespository;
    @Mock
    private BankDeatilsRepository bankDeatilsRepository;
    @Mock
    private AadhaarCardRepository aadhaarCardRepository;
    @Mock
    private DateUtil dateUtil;

    @Test
    public void getPensionerDetailByAadhaar() throws ParseException {

        // Preparation
        PensionerDetail pensionDetails = new
                PensionerDetail("Vaibhav","DVSZ76IT",
                dateUtil.getDateFromString("14/5/2000"),30000.0,
                1000,true,new BankDetail("HDFC",
                "555555555",true));

        AadhaarCard aadhaarCard = new AadhaarCard("123456789","CSID40YF7");

        when(aadhaarCardRepository.findById(Mockito.anyString()))
                .thenReturn(java.util.Optional.of(aadhaarCard));
        when(pensionerDetailsRespository.findById(Mockito.anyString()))
                .thenReturn(java.util.Optional.of(pensionDetails));

        PensionerDetail pensionerDetailResponse = pensionerDetailService.getPensionerDetailByAadhaar("123456789");

        Assert.assertNotNull(pensionDetails);
        Assert.assertEquals(pensionerDetailResponse.getName(),"Vaibhav");
    }
}