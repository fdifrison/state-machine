package com.fdifrison.statemachine.event;

import com.fdifrison.dto.Request;
import com.fdifrison.service.EventLogService;
import com.fdifrison.service.JwtService;
import com.fdifrison.statemachine.state.State;
import com.fdifrison.statemachine.state.StateResolver;

public record SubmitEvent(State from, State.InReview to, Request.SubmitRequest request) implements Event.UserEvent {

    public SubmitEvent {
        if (!(from instanceof State.InProgress))
            throw new IllegalStateException("State must be " + StateResolver.Status.IN_PROGRESS);
    }

    public SubmitEvent(State from, Request.SubmitRequest request) {
        this(from, new State.InReview(StateResolver.Status.IN_REVIEW) , request);
    }

    @Override
    public void logEvent(EventLogService logService) {
        logService.logEvent(this);
    }

    @Override
    public void guard(JwtService jwtService) {
    }

    public Request handleEvent() {
        System.out.println("SubmitEvent.handleEvent()");
        System.out.println("Find entity by id: " + request.id());
        System.out.println(request);
        System.out.println("Updating entity state to " + this.to);
        var updated = new Request.SubmitRequest(request.id(), request.payload(), StateResolver.resolve(this.to()));
        System.out.println(updated);
        return updated;
    }

}
