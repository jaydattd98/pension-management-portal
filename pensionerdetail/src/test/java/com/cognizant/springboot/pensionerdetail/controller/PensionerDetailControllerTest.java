package com.cognizant.springboot.pensionerdetail.controller;

import com.cognizant.springboot.pensionerdetail.entity.BankDetail;
import com.cognizant.springboot.pensionerdetail.entity.PensionerDetail;
import com.cognizant.springboot.pensionerdetail.repository.AadhaarCardRepository;
import com.cognizant.springboot.pensionerdetail.repository.BankDeatilsRepository;
import com.cognizant.springboot.pensionerdetail.repository.PensionerDetailsRespository;
import com.cognizant.springboot.pensionerdetail.services.PensionerDetailService;
import com.cognizant.springboot.pensionerdetail.util.DateUtil;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@WebMvcTest(PensionerDetailController.class)
public class PensionerDetailControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private DateUtil dateUtil;
    @MockBean
    private PensionerDetailService pensionerDetailService;
    @MockBean
    private PensionerDetailsRespository pensionerDetailsRespository;
    @MockBean
    private BankDeatilsRepository bankDeatilsRepository;
    @MockBean
    private AadhaarCardRepository aadhaarCardRepository;

    @Test
    public void pensionerDetailByAadhaarTest() throws Exception {

        // Preparation
        PensionerDetail pensionDetails = new
                PensionerDetail("Vaibhav","DVSZ76IT",
                dateUtil.getDateFromString("14/5/2000"),30000.0,
                1000,true,new BankDetail("HDFC",
                "555555555",true));

        //Mocking service response
        given(pensionerDetailService.getPensionerDetailByAadhaar(Mockito.anyString())).willReturn(pensionDetails);

        //Action
        mvc
                .perform(MockMvcRequestBuilders
                        .get("/PensionerDetailByAadhaar?aadhaarNumber=123456789"))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                                .value("Vaibhav"));

    }

    @Test
    public void pensionerDetailByAadhaarTestNotFoundEx() throws Exception {

        //Mocking service response
        given(pensionerDetailService.getPensionerDetailByAadhaar(Mockito.anyString())).willReturn(null);

        //Action
        mvc
                .perform(MockMvcRequestBuilders
                        .get("/PensionerDetailByAadhaar?aadhaarNumber=12345678"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void pensionerDetailByAadhaarTestInternalServerEx() throws Exception {

        //Mocking service response
        given(pensionerDetailService.getPensionerDetailByAadhaar(Mockito.anyString()))
                .willThrow(new InternalException("Exception got"));

        //Action
        mvc
                .perform(
                        MockMvcRequestBuilders
                            .get("/PensionerDetailByAadhaar?aadhaarNumber=12345678"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void pensionerDetailByAadhaarTestInputNullEx() throws Exception {

        //Action
        mvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/PensionerDetailByAadhaar?aadhaarNumber="))
                .andExpect(status().isBadRequest());

    }
}