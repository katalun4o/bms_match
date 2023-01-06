package com.rado.bms.matchingservicedemo.matching;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchRecord {
    private MessageType messageType;
    private String messageId;
    private MessageStatus status;
}
