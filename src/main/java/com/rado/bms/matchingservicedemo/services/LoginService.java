package com.rado.bms.matchingservicedemo.services;

import com.rado.bms.matchingservicedemo.dtos.LoginResultDTO;
import org.springframework.beans.factory.annotation.Autowired;


public class LoginService {

    @Autowired
    private EligibilityService eligibilityService;

    public LoginResultDTO login(String id) {
        // call all endpoints to check eligibility

        EligibilityResult eligibilityResult = eligibilityService.checkEligibility(id);

        if (eligibilityResult.isEmployed()) {
            return new LoginResultDTO(401,"Unauthorized - employed", eligibilityResult);
        }

        return new LoginResultDTO(200,"Success");
    }




}
