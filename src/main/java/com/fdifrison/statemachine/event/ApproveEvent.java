package com.fdifrison.statemachine.event;

import com.fdifrison.dto.Request;
import com.fdifrison.service.EventLogService;
import com.fdifrison.service.JwtService;
import com.fdifrison.statemachine.state.State;
import com.fdifrison.statemachine.state.StateResolver;

public record ApproveEvent(State from, State.Approved to, Request.ApproveRequest request) implements Event.AdminEvent {

    public ApproveEvent {
        if (!(from instanceof State.InReview))
            throw new IllegalStateException("State must be " + StateResolver.Status.IN_REVIEW);
    }

    public ApproveEvent(State from, Request.ApproveRequest request) {
        this(from, new State.Approved(StateResolver.Status.APPROVED), request);
    }

    @Override
    public void logEvent(EventLogService logService) {
        logService.logEvent(this);
    }

    @Override
    public void guard(JwtService jwtService) {
        AdminEvent.super.guard(jwtService);
    }

    @Override
    public Request handleEvent() {
        System.out.println("ApproveEvent.handleEvent()");
        System.out.println("Find entity by id: " + request.id());
        System.out.println(request);
        System.out.println("Updating entity state to " + this.to);
        var updated = new Request.ApproveRequest(request.id(), request.payload(), StateResolver.resolve(this.to()));
        System.out.println(updated);
        return updated;
    }


}
