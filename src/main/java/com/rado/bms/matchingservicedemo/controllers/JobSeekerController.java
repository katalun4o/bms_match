package com.rado.bms.matchingservicedemo.controllers;

import com.rado.bms.matchingservicedemo.dtos.LoginResultDTO;
import com.rado.bms.matchingservicedemo.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobSeekerController {

    @Autowired
    private LoginService loginService;

    @GetMapping(path = "login/{id}")
    public LoginResultDTO login(@PathVariable("id") String id) {
        return loginService.login(id);
    }
}
