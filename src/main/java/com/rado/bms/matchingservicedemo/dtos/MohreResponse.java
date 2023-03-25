package com.rado.bms.matchingservicedemo.dtos;

import lombok.Data;

@Data
public class MohreResponse implements EmploymentAPI{
    Boolean isEmployed;
    String employerName;
    String occupation;

    @Override
    public boolean isEmployed() {
        return isEmployed;
    }

    @Override
    public String getEmployerName() {
        return employerName;
    }

    @Override
    public String getOccupation() {
        return occupation;
    }
}
