package com.rado.bms.matchingservicedemo.matching.exec

import spock.lang.Specification

import com.rado.bms.matchingservicedemo.matching.MessageType

class MatchExecutorFactoryTest extends Specification {

    def "GetExecutor - Job Seeker"() {
        given:
        IMatchExecutorFactory matchExecutorFactory = new MatchExecutorFactory(
                jobSeekerMatchExecutor: new JobSeekerMatchExecutor(),
                jobPostingMatchExecutor: new JobPostingMatchExecutor())

        when:
        IMatchExecutor jsExecutor = matchExecutorFactory.getExecutor(MessageType.JOB_SEEKER)

        then:
        assert jsExecutor instanceof JobSeekerMatchExecutor
    }

    def "GetExecutor - Job Posting"() {
        given:
        IMatchExecutorFactory matchExecutorFactory = new MatchExecutorFactory(
                jobSeekerMatchExecutor: new JobSeekerMatchExecutor(),
                jobPostingMatchExecutor: new JobPostingMatchExecutor())

        when:
        IMatchExecutor jsExecutor = matchExecutorFactory.getExecutor(MessageType.JOB_POSTING)

        then:
        assert jsExecutor instanceof JobPostingMatchExecutor
    }
}
