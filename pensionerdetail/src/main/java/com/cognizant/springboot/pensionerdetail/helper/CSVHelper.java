package com.cognizant.springboot.pensionerdetail.helper;

import com.cognizant.springboot.pensionerdetail.entity.BankDetail;
import com.cognizant.springboot.pensionerdetail.entity.PensionerDetail;
import com.cognizant.springboot.pensionerdetail.util.DateUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class CSVHelper
 * <p>
 * Used to fetch details from pensionerDetails.txt file and store it in DB
 *
 * @author 841771 jaydatt
 */
@Component
public class CSVHelper {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CSVHelper.class);

    @Autowired
    private DateUtil dateUtil;

    /**
     * Used to Convert csv file details to PensionerDetails object list
     *
     * @param is
     * @return pensionerDetailList
     */
    public List<PensionerDetail> csvToPensionerDetails(InputStream is) {

        String methodName = "CSVHelper#csvToPensionerDetails";
        log.info("inside the " + methodName);

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<PensionerDetail> pensionerDetailList = new ArrayList<PensionerDetail>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                PensionerDetail pensionerDetail = new PensionerDetail(
                        csvRecord.get("name"),
                        csvRecord.get("pan"),
                        dateUtil.getDateFromString(csvRecord.get("dob")),
                        Double.parseDouble(csvRecord.get("salary")),
                        Double.parseDouble(csvRecord.get("allowance")),
                        Boolean.parseBoolean(csvRecord.get("typeOfPention")),
                        new BankDetail(
                                csvRecord.get("bank"),
                                csvRecord.get("accountNumber"),
                                Boolean.parseBoolean(csvRecord.get("isPrivateBank"))
                        )
                );
                pensionerDetailList.add(pensionerDetail);
            }
            log.info("End the "+methodName +" CSVRecord in object list ="+pensionerDetailList.toString());
            return pensionerDetailList;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
