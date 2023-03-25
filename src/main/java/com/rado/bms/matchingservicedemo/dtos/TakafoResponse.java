package com.rado.bms.matchingservicedemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TakafoResponse implements EmploymentAPI{
    Boolean isEmployed;
    String employerName;
    String jobTitle;

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
        return jobTitle;
    }
}
