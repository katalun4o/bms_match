package com.rado.bms.matchingservicedemo.matching.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value = "JobPostingMatchExecutor")
public class JobPostingMatchExecutor implements IMatchExecutor {
    private Logger LOG = LoggerFactory.getLogger(JobPostingMatchExecutor.class);

    @Override
    public void execute(String id) {
        LOG.info("JobPosting matching executor started for id: " + id);
        // simulate matching with thread sleep 2sec
        // FIXME: delete when matching is added
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
