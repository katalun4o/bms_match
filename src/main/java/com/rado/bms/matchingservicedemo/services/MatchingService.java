package com.rado.bms.matchingservicedemo.services;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rado.bms.matchingservicedemo.dtos.MatchingResult;
import com.rado.bms.matchingservicedemo.matching.MatchingQueue;
import com.rado.bms.matchingservicedemo.matching.MessageType;

@Service
public class MatchingService {
    private Logger LOG = LoggerFactory.getLogger(MatchingService.class);

    @Autowired
    private MatchingQueue queue;

    @Autowired
    private MatchingProcessor matchingProcessor;

    @PostConstruct
    public void afterInit() {
        matchingProcessor.startMatchProcessor();
    }

    public MatchingResult matchJobSeeker(String id) {
        LOG.info("matchJobSeeker with id: " + id);
        // add to queue
        queue.put(MessageType.JOB_SEEKER, id);

        return new MatchingResult("JobSeeker added to queue");
    }

    public MatchingResult matchJobPosting(String id) {
        LOG.info("matchJobPosting with id: " + id);
        // add to queue
        queue.put(MessageType.JOB_POSTING, id);

        return new MatchingResult("JobPosting added to queue");
    }
}
