package com.rado.bms.matchingservicedemo.matching.exec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.rado.bms.matchingservicedemo.matching.MessageType;

@Component
public class MatchExecutorFactory implements IMatchExecutorFactory {
    @Autowired
    @Qualifier("JobSeekerMatchExecutor")
    private IMatchExecutor jobSeekerMatchExecutor;

    @Autowired
    @Qualifier("JobPostingMatchExecutor")
    private IMatchExecutor jobPostingMatchExecutor;

    public IMatchExecutor getExecutor(MessageType messageType) {
        switch (messageType) {
            case JOB_SEEKER:
                return jobSeekerMatchExecutor;
            case JOB_POSTING:
                return jobPostingMatchExecutor;
            default:
                throw new RuntimeException();
        }
    }
}
