package com.rado.bms.matchingservicedemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rado.bms.matchingservicedemo.dtos.MatchingResult;
import com.rado.bms.matchingservicedemo.services.MatchingService;

@RestController
public class MatchingController {

    @Autowired
    private MatchingService matchingService;

    @GetMapping(path = "matchJS/{id}")
    public MatchingResult matchJobSeeker(@PathVariable("id") String id) {
        return matchingService.matchJobSeeker(id);
    }

    @GetMapping(path = "matchJP/{id}")
    public MatchingResult matchJobPosting(@PathVariable("id") String id) {
        return matchingService.matchJobPosting(id);
    }
}
