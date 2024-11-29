package com.fdifrison.service;

import com.fdifrison.dto.Request;
import com.fdifrison.event.*;
import com.fdifrison.state.State;

public class StateService {

    private final EventLogService eventLogService;

    public StateService(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }

    public State handleEvent(Request request, Event event) {
        event.guardEvent();
        event.logEvent();
        return switch (event) {
            case AdminEvent admin -> switch (admin) {
                case ApproveEvent approveEvent ->  approveEvent.handleEvent(request);
                case RejectEvent rejectEvent ->  rejectEvent.handleEvent(request);
            };
            case UserEvent user -> switch (user) {
                case SubmitEvent submitEvent -> {
                    eventLogService.logEvent(request, submitEvent);
                    yield submitEvent.handleEvent(request);
                }
            };
        };
    }
}
