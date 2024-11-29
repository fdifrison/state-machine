package com.fdifrison.event;

import com.fdifrison.dto.Request;
import com.fdifrison.state.State;

public sealed interface Event permits UserEvent, AdminEvent {


    void logEvent();
    void guardEvent();
    State handleEvent(Request request);

}

