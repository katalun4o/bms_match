package com.rado.bms.matchingservicedemo.matching.exec;

import com.rado.bms.matchingservicedemo.matching.MessageType;

public interface IMatchExecutorFactory {
    IMatchExecutor getExecutor(MessageType messageType);
}
