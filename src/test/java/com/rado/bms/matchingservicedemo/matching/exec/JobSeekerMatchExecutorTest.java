package com.rado.bms.matchingservicedemo.matching.exec;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobSeekerMatchExecutorTest {

    @Test
    void execute() {
        JobSeekerMatchExecutor executor = new JobSeekerMatchExecutor();

        executor.execute("1234");
    }
}