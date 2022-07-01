package com.cognizant.springboot.processpension.entity;

public class PensionDetail {

    private double pensionAmount;
    private int bankServiceCharge;

    public PensionDetail() {
    }

    public PensionDetail(double pensionAmount, int bankServiceCharge) {
        this.pensionAmount = pensionAmount;
        this.bankServiceCharge = bankServiceCharge;
    }

    public double getPensionAmount() {
        return pensionAmount;
    }

    public void setPensionAmount(double pensionAmount) {
        this.pensionAmount = pensionAmount;
    }

    public int getBankServiceCharge() {
        return bankServiceCharge;
    }

    public void setBankServiceCharge(int bankServiceCharge) {
        this.bankServiceCharge = bankServiceCharge;
    }
}
