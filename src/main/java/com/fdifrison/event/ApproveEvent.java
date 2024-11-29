package com.fdifrison.event;

import com.fdifrison.dto.Request;
import com.fdifrison.state.Approved;
import com.fdifrison.state.InReview;
import com.fdifrison.state.State;

public record ApproveEvent(State from, Approved to) implements AdminEvent {

    public ApproveEvent {
        if (!(from instanceof InReview)) throw new IllegalStateException("State must be in review");
    }

    @Override
    public void logEvent() {
        System.out.println("Approve: from=" + from + " to=" + to);
    }

    @Override
    public State handleEvent(Request request) {
        return null;
    }


}
