package com.cognizant.springboot.pensionerdetail.util;

import com.cognizant.springboot.pensionerdetail.services.impl.AuthServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class DateUtilTest {

    @InjectMocks
    private DateUtil dateUtil;

    @Test
    public void getDateFromString() throws ParseException {

        Date date = dateUtil.getDateFromString("14/08/1998");
        Assert.assertNotNull(date);
    }
}