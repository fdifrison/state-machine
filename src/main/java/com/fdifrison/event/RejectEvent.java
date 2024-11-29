package com.fdifrison.event;

import com.fdifrison.dto.Request;
import com.fdifrison.state.InProgress;
import com.fdifrison.state.InReview;
import com.fdifrison.state.Rejected;
import com.fdifrison.state.State;

public record RejectEvent(State from, Rejected to) implements AdminEvent {

    public RejectEvent {
        if (!(from instanceof InReview)) throw new IllegalStateException("State must be in review");
    }

    @Override
    public void logEvent() {
        System.out.println("Reject: from=" + from + " to=" + to);
    }

    @Override
    public State handleEvent(Request request) {
        return null;
    }

}
