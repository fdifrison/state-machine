package com.fdifrison.statemachine;

import com.fdifrison.dto.Request;
import com.fdifrison.service.EventLogService;
import com.fdifrison.service.JwtService;
import com.fdifrison.statemachine.event.ApproveEvent;
import com.fdifrison.statemachine.event.Event;
import com.fdifrison.statemachine.event.RejectEvent;
import com.fdifrison.statemachine.event.SubmitEvent;
import org.springframework.stereotype.Service;

@Service
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
