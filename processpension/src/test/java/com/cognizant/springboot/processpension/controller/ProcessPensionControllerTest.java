package com.cognizant.springboot.processpension.controller;

import com.cognizant.springboot.processpension.entity.PensionDetail;
import com.cognizant.springboot.processpension.entity.ProcessPensionInput;
import com.cognizant.springboot.processpension.entity.SessionData;
import com.cognizant.springboot.processpension.helper.ProcessPensionHelper;
import com.cognizant.springboot.processpension.services.ProcessPensionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@WebMvcTest(ProcessPensionController.class)
public class ProcessPensionControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private SessionData sessionData;
    @MockBean
    private ProcessPensionService processPensionService;
    @MockBean
    private ProcessPensionHelper processPensionHelper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void processPensionTest() throws Exception {

        PensionDetail pensionDetail = new PensionDetail(500005.5, 500);

        //Mocking service response
        when(processPensionService.getProcessPension(Mockito.any()))
                .thenReturn(pensionDetail);

        //Action
        mvc
                .perform(MockMvcRequestBuilders
                        .post("/ProcessPension")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ProcessPensionInput("12345"))))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pensionAmount")
                        .value(500005.5));
    }

    @Test
    public void processPensionNullInputTest() throws Exception {

        PensionDetail pensionDetail = new PensionDetail(500005.5, 500);

        //Mocking service response
        when(processPensionService.getProcessPension(Mockito.any()))
                .thenReturn(pensionDetail);

        try {
            //Action
            mvc
                    .perform(MockMvcRequestBuilders
                            .post("/ProcessPension")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(new ProcessPensionInput(""))))
                    .andExpect(status().isBadRequest());
        } catch (InternalException e) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void processPensionInternalExTest() throws Exception {

        //Mocking service response
        when(processPensionService.getProcessPension(Mockito.any()))
                .thenThrow(new InternalException("Exception caught"));

        try {
            //Action
            mvc
                    .perform(MockMvcRequestBuilders
                            .post("/ProcessPension")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(new ProcessPensionInput("1234567"))))
                    .andExpect(status().isInternalServerError());
        } catch (InternalException e) {
            Assert.assertTrue(true);
        }

    }
}