package com.cognizant.springboot.processpension.helper;

import com.cognizant.springboot.processpension.entity.BankDetail;
import com.cognizant.springboot.processpension.entity.PensionerDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

@RunWith(SpringRunner.class)
public class ProcessPensionHelperTest {

    @InjectMocks
    private ProcessPensionHelper processPensionHelper;
    private PensionerDetail pensionDetails = new
            PensionerDetail(
            "Vaibhav",
            "DVSZ76IT",
            new Date(),
            100000,
            1000,
            true,
            new BankDetail("HDFC", "555555555", true));

    @Test
    public void calculatePensionAmountTest() {

        ReflectionTestUtils.setField(processPensionHelper, "selfPensionInPercent", 0.8);
        ReflectionTestUtils.setField(processPensionHelper, "familyPensionInPercent", 0.5);

        double response = processPensionHelper.calculatePensionAmount(pensionDetails);
        Assert.assertNotNull(response);
        Assert.assertEquals(81000.0, response, 0.1);
    }

    @Test
    public void calculatePensionAmountNullTest() {

        ReflectionTestUtils.setField(processPensionHelper, "selfPensionInPercent", 0.8);
        ReflectionTestUtils.setField(processPensionHelper, "familyPensionInPercent", 0.5);

        double response = processPensionHelper.calculatePensionAmount(null);
        Assert.assertNotNull(response);
        Assert.assertEquals(0, response, 0.1);
    }

    @Test
    public void calculateBankServiceChargeTest() {

        ReflectionTestUtils.setField(processPensionHelper, "privateBankServiceCharge", 550);
        ReflectionTestUtils.setField(processPensionHelper, "publicBankServiceCharge", 500);
        int response = processPensionHelper.calculateBankServiceCharge(pensionDetails);
        Assert.assertNotNull(response);
        Assert.assertEquals(550, response);
    }

    @Test
    public void calculateBankServiceChargeNullTest() {

        ReflectionTestUtils.setField(processPensionHelper, "privateBankServiceCharge", 550);
        ReflectionTestUtils.setField(processPensionHelper, "publicBankServiceCharge", 500);
        int response = processPensionHelper.calculateBankServiceCharge(null);
        Assert.assertNotNull(response);
        Assert.assertEquals(0, response);
    }
}