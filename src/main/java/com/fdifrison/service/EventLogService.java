package com.fdifrison.service;

import com.fdifrison.dto.EventLog;
import com.fdifrison.dto.Request;
import com.fdifrison.event.ApproveEvent;
import com.fdifrison.event.Event;
import com.fdifrison.event.RejectEvent;
import com.fdifrison.event.SubmitEvent;
import com.fdifrison.repository.EventLogRepository;
import com.fdifrison.state.State;

public class EventLogService {

    private final JwtService jwtService;
    private final EventLogRepository eventLogRepository;

    public EventLogService(JwtService jwtService, EventLogRepository eventLogRepository) {
        this.jwtService = jwtService;
        this.eventLogRepository = eventLogRepository;
    }

    public void logEvent(Event event) {
        switch (event) {
            case ApproveEvent(State from, State to, Request.ApproveRequest request) -> eventLogRepository.save(
                    new EventLog(
                            request.id(),
                            from,
                            to,
                            jwtService.getUserRole(),
                            event.getClass().getSimpleName(),
                            request.payload().approvedBy(),
                            ""));
            case RejectEvent(State from, State to, Request.RejectRequest request) -> eventLogRepository.save(
                    new EventLog(
                            request.id(),
                            from,
                            to,
                            jwtService.getUserRole(),
                            event.getClass().getSimpleName(),
                            request.payload().rejectedBy(),
                            request.payload().cause()));
            case SubmitEvent(State from, State to, Request.SubmitRequest request) -> eventLogRepository.save(
                    new EventLog(
                            request.id(),
                            from,
                            to,
                            jwtService.getUserRole(),
                            event.getClass().getSimpleName(),
                            "",
                            ""));
        }
    }
}
