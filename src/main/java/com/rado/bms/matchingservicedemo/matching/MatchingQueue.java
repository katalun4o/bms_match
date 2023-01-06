package com.rado.bms.matchingservicedemo.matching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class MatchingQueue {
    private Logger LOG = LoggerFactory.getLogger(MatchingQueue.class);

    private List<MatchRecord> matchingRecords;
    private List<MatchRecord> pendingRecordsQueue;

    private final Object mutex = new Object();

    public MatchingQueue() {
        this.matchingRecords = Collections.synchronizedList(new ArrayList<>());
        this.pendingRecordsQueue = Collections.synchronizedList(new ArrayList<>());
    }

    public MatchRecord put(@NonNull MessageType messageType, @NonNull String messageId) {
        LOG.info("this.matchingRecords size is:" + this.matchingRecords.size());

        LOG.debug("inputRecord messageId:" + messageId);
        LOG.debug("inputRecord messageType:" + messageType);

        for(MatchRecord cachedRecord : this.matchingRecords) {
            LOG.debug("cachedRecord messageId:" + cachedRecord.getMessageId());
            LOG.debug("cachedRecord messageType:" + cachedRecord.getMessageType());
            LOG.debug("cachedRecord status:" + cachedRecord.getStatus());

            if (messageId.equals(cachedRecord.getMessageId()) &&
                    messageType == cachedRecord.getMessageType() &&
                    cachedRecord.getStatus() == MessageStatus.PENDING) {
                LOG.info("MatchRecord already exists. Not added to queue:" + messageId);
                return null;
            }
        }

        MatchRecord record = new MatchRecord(messageType, messageId, MessageStatus.PENDING);
        this.matchingRecords.add(record);
        this.pendingRecordsQueue.add(record);
        return record;
    }

    public List<MatchRecord> getMatchingRecords() {
        return matchingRecords;
    }

    public List<MatchRecord> getPendingRecords() {
        return pendingRecordsQueue;
    }


    public void delete(MatchRecord matchRecord) {
        synchronized (mutex) {
            matchingRecords.remove(matchRecord);
        }
    }

    public MatchRecord poll() {
        return pendingRecordsQueue.remove(0);
    }

    public boolean canProcess(MatchRecord record) {
        if(record == null) {
            return false;
        }

        boolean canProcess;

        synchronized (mutex) {
            canProcess = matchingRecords
                    .stream()
                    .noneMatch(m -> m != null
                            && m.getMessageId().equals(record.getMessageId())
                            && m.getMessageType().equals(record.getMessageType())
                            && m.getStatus() == MessageStatus.IN_PROGRESS);
        }
        return canProcess;
    }
}
