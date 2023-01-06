package com.rado.bms.matchingservicedemo.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PreDestroy;

import com.rado.bms.matchingservicedemo.matching.exec.IMatchExecutorFactory;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.rado.bms.matchingservicedemo.config.Config;
import com.rado.bms.matchingservicedemo.matching.MatchRecord;
import com.rado.bms.matchingservicedemo.matching.MatchingQueue;
import com.rado.bms.matchingservicedemo.matching.MessageStatus;

@Service
public class MatchingProcessor {
    private Logger LOG = LoggerFactory.getLogger(MatchingProcessor.class);

    private ExecutorService executorService;

    @Autowired
    private MatchingQueue matchingQueue;

    @Autowired
    private IMatchExecutorFactory matchExecutorFactory;

    private boolean stopWorker;

    MatchingProcessor(Config config) {
        LOG.info("Creating matching processor with thread pool size: " + config.getThreadPoolSize());
        executorService = Executors.newFixedThreadPool(config.getThreadPoolSize());
    }

    @Async
    void startMatchProcessor() {
        while (!stopWorker) {
            if (matchingQueue.getPendingRecords().isEmpty()) {
                continue;
            }

            MatchRecord record = matchingQueue.poll();

            if (matchingQueue.canProcess(record)) {
                processRecord(record);
            } else {
                matchingQueue.getPendingRecords().add(record);
            }
        }

        LOG.info("Exiting matching processor");
    }

    @PreDestroy
    public void destroy() {
        stopWorker = true;
    }

    private void processRecord(MatchRecord matchRecord) {
        LOG.info("add record for processing: " + matchRecord.getMessageId());
        executorService.submit(new MatchRunnable(matchRecord, matchingQueue, matchExecutorFactory));
    }

    @AllArgsConstructor
    static class MatchRunnable implements Runnable {
        private static Logger LOG = LoggerFactory.getLogger(MatchRunnable.class);
        private MatchRecord matchRecord;
        private MatchingQueue matchingQueue;
        private IMatchExecutorFactory matchExecutorFactory;

        @Override
        public void run() {
            LOG.info("Start matching " + matchRecord.getMessageType() + " with id: " + matchRecord.getMessageId());
            matchRecord.setStatus(MessageStatus.IN_PROGRESS);

            matchExecutorFactory
                    .getExecutor(matchRecord.getMessageType())
                    .execute(matchRecord.getMessageId());

            LOG.info("Matching finished for " + matchRecord.getMessageType() + ". Deleting " + matchRecord.getMessageId());
            // after matching delete records from queue
            matchingQueue.delete(matchRecord);
        }
    }
}
