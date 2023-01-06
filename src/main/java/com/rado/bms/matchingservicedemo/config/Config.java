package com.rado.bms.matchingservicedemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${matching-thread-pool-size}")
    private int threadPoolSize = 4;

    public int getThreadPoolSize() {
        return threadPoolSize;
    }
}
