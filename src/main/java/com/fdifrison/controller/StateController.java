package com.fdifrison.controller;

import com.fdifrison.dto.Request;
import com.fdifrison.event.SubmitEvent;
import com.fdifrison.service.StateService;
import com.fdifrison.state.InReview;
import com.fdifrison.state.State;
import com.fdifrison.state.StateResolver;

public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    public State Submit(Request request) {
        var reqestState = StateResolver.resolve(request.status());
        return stateService.handleEvent(request, new SubmitEvent(reqestState, new InReview(), this.getClass().getSimpleName()));
    }

}
