package com.fdifrison.event;

import com.fdifrison.dto.Request;
import com.fdifrison.service.EventLogService;
import com.fdifrison.state.State;
import com.fdifrison.state.StateResolver;

public record RejectEvent(State from, State.Rejected to, Request.RejectRequest request) implements Event.AdminEvent {

    public RejectEvent {
        if (!(from instanceof State.InReview))
            throw new IllegalStateException("State must be " + StateResolver.Status.IN_REVIEW);
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
        var updated = new Request.RejectRequest(request.id(), request.payload(), StateResolver.resolve(this.to()));
        System.out.println(updated);
        return updated;
    }

}
