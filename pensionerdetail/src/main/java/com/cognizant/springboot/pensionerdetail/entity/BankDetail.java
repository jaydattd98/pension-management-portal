package com.cognizant.springboot.pensionerdetail.entity;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@ToString
@Entity
public class BankDetail {
    private String bankName;
    @Id
    private String accountNumber;
    private boolean privateBank;

    public BankDetail() {
    }

    public BankDetail(String bankName, String accountNumber, boolean privateBank) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.privateBank = privateBank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isPrivateBank() {
        return privateBank;
    }

    public void setPrivateBank(boolean privateBank) {
        this.privateBank = privateBank;
    }
}
