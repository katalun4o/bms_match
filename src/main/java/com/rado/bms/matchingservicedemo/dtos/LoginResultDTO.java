package com.rado.bms.matchingservicedemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResultDTO {
    int code;
    String desc;
    Object data;

    public LoginResultDTO(int code, String desc) {
        this(code, desc, null);
    }
}
