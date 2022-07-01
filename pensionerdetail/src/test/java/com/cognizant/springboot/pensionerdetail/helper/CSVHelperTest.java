package com.cognizant.springboot.pensionerdetail.helper;

import com.cognizant.springboot.pensionerdetail.entity.PensionerDetail;
import com.cognizant.springboot.pensionerdetail.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
public class CSVHelperTest {

    @InjectMocks
    private CSVHelper csvHelper;

    @Mock
    private DateUtil dateUtil;

    @Test
    public void csvToPensionerDetailsTest() throws IOException {

        InputStream is = new ClassPathResource("/pensionerDetails.txt").getInputStream();
        List<PensionerDetail> pensionerDetailList = csvHelper.csvToPensionerDetails(is);
        Assert.assertEquals(3, pensionerDetailList.size());

    }
}