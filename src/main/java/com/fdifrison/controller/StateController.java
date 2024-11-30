package com.fdifrison.controller;

import com.fdifrison.dto.Request;
import com.fdifrison.statemachine.StateMachineService;
import com.fdifrison.statemachine.event.ApproveEvent;
import com.fdifrison.statemachine.event.RejectEvent;
import com.fdifrison.statemachine.event.SubmitEvent;
import com.fdifrison.statemachine.state.StateResolver;

public class StateController {

    private final StateMachineService stateMachineService;

    public StateController(StateMachineService stateMachineService) {
        this.stateMachineService = stateMachineService;
    }

    public Request submit(Request.SubmitRequest request) {
        var requestState = StateResolver.resolve(request.status());
        return stateMachineService.handleEvent(new SubmitEvent(requestState, request));
    }

    public Request reject(Request.RejectRequest request) {
        var requestState = StateResolver.resolve(request.status());
        return stateMachineService.handleEvent(new RejectEvent(requestState, request));
    }

    public Request approve(Request.ApproveRequest request) {
        var requestState = StateResolver.resolve(request.status());
        return stateMachineService.handleEvent(new ApproveEvent(requestState, request));
    }

}
