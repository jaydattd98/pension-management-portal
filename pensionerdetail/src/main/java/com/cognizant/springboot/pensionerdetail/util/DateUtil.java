package com.cognizant.springboot.pensionerdetail.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class DateUtil
 * Used to perform the different operation on Date
 *
 * @author 841771 jaydatt
 */
@Component
public class DateUtil {

    public Date getDateFromString(String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateWithZeroTime = formatter.parse(formatter.format(formatter.parse(date)));
        return dateWithZeroTime;
    }
}
