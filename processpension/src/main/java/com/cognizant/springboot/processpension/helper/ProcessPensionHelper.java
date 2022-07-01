package com.cognizant.springboot.processpension.helper;

import com.cognizant.springboot.processpension.entity.PensionerDetail;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * ProcessPensionHelper
 *
 * @author 841771 jaydatt
 */
@Service
public class ProcessPensionHelper {


    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ProcessPensionHelper.class);

    @Value("${bank.privateBankServiceCharge}")
    private int privateBankServiceCharge;

    @Value("${bank.publicBankServiceCharge}")
    private int publicBankServiceCharge;

    @Value("${pensionAmountInPercent.selfPension}")
    private double selfPensionInPercent;

    @Value("${pensionAmountInPercent.familyPension}")
    private double familyPensionInPercent;

    /**
     * this method calculate the pension amount
     *
     * @param pensionerDetail
     * @return pentionAmount
     */
    public double calculatePensionAmount(PensionerDetail pensionerDetail) {
        if (pensionerDetail != null) {
            return pensionerDetail.isSelfPension() ? (selfPensionInPercent * pensionerDetail.getSalaryEarned()) + pensionerDetail.getAllowances() :
                    (familyPensionInPercent * pensionerDetail.getSalaryEarned()) + pensionerDetail.getAllowances();
        } else {
            log.warn("pensionerDetail is null, So setting pensionAmount=0");
            return 0;
        }
    }

    /**
     * this method calculate the bank service charge
     *
     * @param pensionerDetail
     * @return bankServiceCharge
     */
    public int calculateBankServiceCharge(PensionerDetail pensionerDetail) {
        if (pensionerDetail != null && pensionerDetail.getBankDetail() != null) {
            return pensionerDetail.getBankDetail().isPrivateBank() ? privateBankServiceCharge : publicBankServiceCharge;
        } else {
            log.warn("pensionerDetail is null, So setting BankServiceCharge=0");
            return 0;
        }
    }
}
