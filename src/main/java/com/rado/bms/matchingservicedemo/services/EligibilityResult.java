package com.rado.bms.matchingservicedemo.services;

import com.rado.bms.matchingservicedemo.dtos.EmploymentAPI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EligibilityResult {

    boolean isEmployed;
    String statusDesc;
    String service;
    String employerName;
    String occupation;

    public static EligibilityResult notEmployed() {
        return new EligibilityResult();
    }
}
