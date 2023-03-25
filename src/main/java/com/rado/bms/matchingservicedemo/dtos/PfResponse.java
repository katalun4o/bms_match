package com.rado.bms.matchingservicedemo.dtos;

import lombok.Data;

@Data
public class PfResponse implements EmploymentAPI{
    Boolean isEmployed;
    String employer;
    String jobTitle;

    @Override
    public boolean isEmployed() {
        return isEmployed;
    }

    @Override
    public String getEmployerName() {
        return employer;
    }

    @Override
    public String getOccupation() {
        return jobTitle;
    }
}
