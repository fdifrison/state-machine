package com.fdifrison.service;

import com.fdifrison.dto.EventLog;
import com.fdifrison.dto.Request;
import com.fdifrison.statemachine.event.ApproveEvent;
import com.fdifrison.statemachine.event.Event;
import com.fdifrison.statemachine.event.RejectEvent;
import com.fdifrison.statemachine.event.SubmitEvent;
import com.fdifrison.repository.EventLogRepository;
import com.fdifrison.statemachine.state.State;
import org.springframework.stereotype.Service;

@Service
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
