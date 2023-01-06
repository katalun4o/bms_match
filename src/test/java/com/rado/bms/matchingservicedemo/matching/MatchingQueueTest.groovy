package com.rado.bms.matchingservicedemo.matching

import spock.lang.Specification

class MatchingQueueTest extends Specification {

    def "Put single element in empty queue"() {
        given:
            def matchingQueue = new MatchingQueue()

        when:
            matchingQueue.put(MessageType.JOB_SEEKER, "1234")

        then:
            assert matchingQueue.getMatchingRecords().equals([new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.PENDING)])
    }

    def "Put existing element in full queue"() {
        given:
        def matchingQueue = new MatchingQueue(matchingRecords:[
                new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.PENDING),
                new MatchRecord(MessageType.JOB_SEEKER, "1235", MessageStatus.PENDING),
                new MatchRecord(MessageType.JOB_SEEKER, "1236", MessageStatus.PENDING)])

        when:
        matchingQueue.put(MessageType.JOB_SEEKER, "1234")

        then:
        assert matchingQueue.getMatchingRecords().equals([
                new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.PENDING),
                new MatchRecord(MessageType.JOB_SEEKER, "1235", MessageStatus.PENDING),
                new MatchRecord(MessageType.JOB_SEEKER, "1236", MessageStatus.PENDING)
        ])
    }

    def "Put InProgress element in full queue"() {
        given:
        def matchingQueue = new MatchingQueue(matchingRecords: [
                new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.IN_PROGRESS),
                new MatchRecord(MessageType.JOB_SEEKER, "1235", MessageStatus.IN_PROGRESS),
                new MatchRecord(MessageType.JOB_SEEKER, "1236", MessageStatus.IN_PROGRESS)])

        when:
        matchingQueue.put(MessageType.JOB_SEEKER, "1234")

        then:
        assert matchingQueue.getMatchingRecords().equals([
                new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.IN_PROGRESS),
                new MatchRecord(MessageType.JOB_SEEKER, "1235", MessageStatus.IN_PROGRESS),
                new MatchRecord(MessageType.JOB_SEEKER, "1236", MessageStatus.IN_PROGRESS),
                new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.PENDING)
        ])
    }

    def "Delete element from full queue"() {
        given:
        MatchRecord recordToDelete = new MatchRecord(MessageType.JOB_SEEKER, "1235", MessageStatus.IN_PROGRESS)
        def matchingQueue = new MatchingQueue(matchingRecords: [
                new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.IN_PROGRESS),
                recordToDelete,
                new MatchRecord(MessageType.JOB_SEEKER, "1236", MessageStatus.IN_PROGRESS)])

        when:
        matchingQueue.delete(recordToDelete)

        then:
        assert matchingQueue.getMatchingRecords().equals([
                new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.IN_PROGRESS),
                new MatchRecord(MessageType.JOB_SEEKER, "1236", MessageStatus.IN_PROGRESS)
        ])
    }

    def "CanProcess element from queue - true"() {
        given:
        def matchingQueue = new MatchingQueue(matchingRecords: [
                new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.PENDING),
                new MatchRecord(MessageType.JOB_SEEKER, "1235", MessageStatus.PENDING)])

        when:
        boolean canProcess = matchingQueue.canProcess(new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.PENDING))

        then:
        assert canProcess
    }

    def "CanProcess element from queue - false"() {
        given:
        def matchingQueue = new MatchingQueue(matchingRecords: [
                new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.IN_PROGRESS),
                new MatchRecord(MessageType.JOB_SEEKER, "1235", MessageStatus.PENDING)])

        when:
        boolean canProcess = matchingQueue.canProcess(new MatchRecord(MessageType.JOB_SEEKER, "1234", MessageStatus.PENDING))

        then:
        assert !canProcess
    }
}
