package com.cognizant.springboot.pensionerdetail.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class AadhaarCard {

    @Id
    private String aadhaarNumber;
    private String panNumber;

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public AadhaarCard(String aadhaarNumber, String panNumber) {
        this.aadhaarNumber = aadhaarNumber;
        this.panNumber = panNumber;
    }
}
