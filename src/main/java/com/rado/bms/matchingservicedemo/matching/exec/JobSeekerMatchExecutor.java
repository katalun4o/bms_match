package com.rado.bms.matchingservicedemo.matching.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value = "JobSeekerMatchExecutor")
public class JobSeekerMatchExecutor implements IMatchExecutor {
    private Logger LOG = LoggerFactory.getLogger(JobSeekerMatchExecutor.class);

    @Override
    public void execute(String id) {
        LOG.info("JobSeeker matching executor started for id: " + id);
        // simulate matching with thread sleep 2sec
        // FIXME: delete when matching is added
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
