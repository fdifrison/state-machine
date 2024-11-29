package com.fdifrison.event;

import com.fdifrison.dto.Request;
import com.fdifrison.state.InProgress;
import com.fdifrison.state.InReview;
import com.fdifrison.state.State;
import com.fdifrison.state.StateResolver;

public record SubmitEvent(State from, InReview to, String initiator) implements UserEvent {

    public SubmitEvent {
        if (!(from instanceof InProgress)) throw new IllegalStateException("State must be in progress");
    }

    @Override
    public void logEvent() {
        System.out.println("Submit: from=" + StateResolver.resolve(from) + " to=" + StateResolver.resolve(to));
    }

    @Override
    public void guardEvent() {
        UserEvent.super.guardEvent();
        System.out.println("Applying the Submit Event Guard");
    }

    public State handleEvent(Request request) {
        System.out.println("Performing custom logic on Submit Event given request");
        System.out.println(request);
        return this.to();
    }

}
