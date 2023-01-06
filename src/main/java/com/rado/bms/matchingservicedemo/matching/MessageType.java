package com.rado.bms.matchingservicedemo.matching;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MessageType {
    JOB_SEEKER("job.seeker"),
    JOB_POSTING("job.posting");

    private String type;
}
