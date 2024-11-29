package com.fdifrison.controller;

import com.fdifrison.dto.Request;
import com.fdifrison.event.ApproveEvent;
import com.fdifrison.event.RejectEvent;
import com.fdifrison.event.SubmitEvent;
import com.fdifrison.service.StateMachineService;
import com.fdifrison.state.State;
import com.fdifrison.state.StateResolver;

public class StateController {

    private final StateMachineService stateMachineService;

    public StateController(StateMachineService stateMachineService) {
        this.stateMachineService = stateMachineService;
    }

    public Request Submit(Request.SubmitRequest request) {
        var requestState = StateResolver.resolve(request.status());
        return stateMachineService.handleEvent(new SubmitEvent(requestState, new State.InReview(StateResolver.Status.IN_REVIEW), request));
    }

    public Request Reject(Request.RejectRequest request) {
        var requestState = StateResolver.resolve(request.status());
        return stateMachineService.handleEvent(new RejectEvent(requestState, new State.Rejected(StateResolver.Status.REJECTED), request));
    }

    public Request Approve(Request.ApproveRequest request) {
        var requestState = StateResolver.resolve(request.status());
        return stateMachineService.handleEvent(new ApproveEvent(requestState, new State.Approved(StateResolver.Status.APPROVED), request));
    }

}
