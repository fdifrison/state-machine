package com.fdifrison.service;

import com.fdifrison.dto.Request;
import com.fdifrison.event.ApproveEvent;
import com.fdifrison.event.Event;
import com.fdifrison.event.RejectEvent;
import com.fdifrison.event.SubmitEvent;

public class StateMachineService {

    private final EventLogService eventLogService;
    private final JwtService jwtService;

    public StateMachineService(EventLogService eventLogService, JwtService jwtService) {
        this.eventLogService = eventLogService;
        this.jwtService = jwtService;
    }

    public Request handleEvent(Event event) {
        event.guard(jwtService);
        event.logEvent(eventLogService);
        return switch (event) {
            case Event.AdminEvent admin -> switch (admin) {
                case ApproveEvent approveEvent -> approveEvent.handleEvent();
                case RejectEvent rejectEvent -> rejectEvent.handleEvent();
            };
            case Event.UserEvent user -> switch (user) {
                case SubmitEvent submitEvent -> submitEvent.handleEvent();
            };
        };
    }


}
