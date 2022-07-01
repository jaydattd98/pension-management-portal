package com.cognizant.springboot.pensionerdetail.services;

import com.cognizant.springboot.pensionerdetail.entity.PensionerDetail;

public interface PensionerDetailService {

    PensionerDetail getPensionerDetailByAadhaar(String aadharNumber);
}
