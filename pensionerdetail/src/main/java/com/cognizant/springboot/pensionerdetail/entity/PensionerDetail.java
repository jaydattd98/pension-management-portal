package com.cognizant.springboot.pensionerdetail.entity;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

@ToString
@Entity
public class PensionerDetail {
    private String name;
    @Id
    private String panNumber;
    private Date dateOfBirth;
    private double salaryEarned;
    private double allowances;
    private boolean isSelfPension;
    @OneToOne
    private BankDetail bankDetail;

    public PensionerDetail() {
    }

    public PensionerDetail(String name, String panNumber, Date dateOfBirth, double salaryEarned, double allowances, boolean isSelfPension, BankDetail bankDetail) {
        this.name = name;
        this.panNumber = panNumber;
        this.dateOfBirth = dateOfBirth;
        this.salaryEarned = salaryEarned;
        this.allowances = allowances;
        this.isSelfPension = isSelfPension;
        this.bankDetail = bankDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalaryEarned() {
        return salaryEarned;
    }

    public void setSalaryEarned(double salaryEarned) {
        this.salaryEarned = salaryEarned;
    }

    public double getAllowances() {
        return allowances;
    }

    public void setAllowances(double allowances) {
        this.allowances = allowances;
    }

    public boolean isSelfPension() {
        return isSelfPension;
    }

    public void setSelfPension(boolean selfPension) {
        isSelfPension = selfPension;
    }

    public BankDetail getBankDetail() {
        return bankDetail;
    }

    public void setBankDetail(BankDetail bankDetail) {
        this.bankDetail = bankDetail;
    }
}
