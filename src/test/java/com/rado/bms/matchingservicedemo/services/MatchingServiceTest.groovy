package com.rado.bms.matchingservicedemo.services

import com.rado.bms.matchingservicedemo.matching.MatchingQueue
import com.rado.bms.matchingservicedemo.matching.MessageType
import spock.lang.Specification

class MatchingServiceTest extends Specification {
    def "MatchJobSeeker"() {
        given:
        def queue = new MatchingQueue()
        MatchingService matchingService = new MatchingService(queue: queue)

        when:
        matchingService.matchJobSeeker("123")

        then:
        assert queue.matchingRecords.size() == 1
        assert queue.pendingRecords.size() == 1

        when:
        def record = queue.poll()

        then:
        assert record.messageId == "123"
        assert record.messageType == MessageType.JOB_SEEKER
        assert queue.pendingRecords.isEmpty()
        assert queue.pendingRecords.size() == 0
    }

    def "MatchJobPosting"() {
        given:
        def queue = new MatchingQueue()
        MatchingService matchingService = new MatchingService(queue: queue)

        when:
        matchingService.matchJobPosting("123")

        then:
        assert queue.matchingRecords.size() == 1
        assert queue.pendingRecords.size() == 1

        when:
        def record = queue.poll()

        then:
        assert record.messageId == "123"
        assert record.messageType == MessageType.JOB_POSTING
        assert queue.pendingRecords.isEmpty()
        assert queue.pendingRecords.size() == 0
    }

    def "Match JobSeeker and JobPosting"() {
        given:
        def queue = new MatchingQueue()
        MatchingService matchingService = new MatchingService(queue: queue)

        when:
        matchingService.matchJobSeeker("1234")
        matchingService.matchJobPosting("1235")

        then:
        assert queue.matchingRecords.size() == 2
        assert queue.pendingRecords.size() == 2

        when:
        def record = queue.poll()

        then:
        assert record.messageId == "1234"
        assert record.messageType == MessageType.JOB_SEEKER
        assert !queue.pendingRecords.isEmpty()
        assert queue.pendingRecords.size() == 1

        when:
        record = queue.poll()

        then:
        assert record.messageId == "1235"
        assert record.messageType == MessageType.JOB_POSTING
        assert queue.pendingRecords.isEmpty()
        assert queue.pendingRecords.size() == 0
    }
}
