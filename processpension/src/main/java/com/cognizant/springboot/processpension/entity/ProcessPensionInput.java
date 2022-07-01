package com.cognizant.springboot.processpension.entity;

public class ProcessPensionInput {

    private String aadhaarNumber;

    public ProcessPensionInput() {
    }

    public ProcessPensionInput(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }
}
