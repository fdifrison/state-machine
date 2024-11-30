package com.fdifrison.controller;

import com.fdifrison.dto.Request;
import com.fdifrison.statemachine.StateMachineService;
import com.fdifrison.statemachine.event.ApproveEvent;
import com.fdifrison.statemachine.event.RejectEvent;
import com.fdifrison.statemachine.event.SubmitEvent;
import com.fdifrison.statemachine.state.State;
import com.fdifrison.statemachine.state.StateResolver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {

    private final StateMachineService stateMachineService;

    public StateController(StateMachineService stateMachineService) {
        this.stateMachineService = stateMachineService;
    }

    @PostMapping("submit")
    public Request submit(@RequestBody Request.SubmitRequest request, State state) {
        return stateMachineService.handleEvent(new SubmitEvent(state, request));
    }

    @PostMapping("reject")
    public Request reject(@RequestBody Request.RejectRequest request, State state) {
        return stateMachineService.handleEvent(new RejectEvent(state, request));
    }

    @PostMapping("approve")
    public Request approve(@RequestBody Request.ApproveRequest request, State state) {
        return stateMachineService.handleEvent(new ApproveEvent(state, request));
    }

}
