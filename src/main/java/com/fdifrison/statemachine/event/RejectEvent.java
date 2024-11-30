package com.fdifrison.statemachine.event;

import com.fdifrison.dto.Request;
import com.fdifrison.service.EventLogService;
import com.fdifrison.statemachine.state.State;
import com.fdifrison.statemachine.state.StateResolver;

public record RejectEvent(State from, State.Rejected to, Request.RejectRequest request) implements Event.AdminEvent {

    public RejectEvent {
        if (!(from instanceof State.InReview))
            throw new IllegalStateException("State must be " + StateResolver.Status.IN_REVIEW);
    }

    public RejectEvent(State from, Request.RejectRequest request) {
        this(from, new State.Rejected(StateResolver.Status.REJECTED), request);
    }

    @Override
    public void logEvent(EventLogService logService) {
        logService.logEvent(this);
    }


    @Override
    public Request handleEvent() {
        System.out.println("RejectEvent.handleEvent()");
        System.out.println("Find entity by id: " + request.id());
        System.out.println(request);
        System.out.println("Updating entity state to " + this.to);
        var updated = new Request.RejectRequest(request.id(), request.payload());
        System.out.println(updated);
        return updated;
    }

}
