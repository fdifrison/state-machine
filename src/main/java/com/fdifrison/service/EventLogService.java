package com.fdifrison.service;

import com.fdifrison.dto.Request;
import com.fdifrison.event.*;
import com.fdifrison.repository.EventLogRepository;

public class EventLogService {

    private final EventLogRepository eventLogRepository;

    public EventLogService(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    public void logEvent(Request request, Event event) {
        switch (event) {
            case ApproveEvent approveEvent -> eventLogRepository.save(request, approveEvent);
            case RejectEvent rejectEvent -> eventLogRepository.save(request, rejectEvent);
            case SubmitEvent submitEvent -> {
                System.out.println("Performing custom logic on Logging Event: " + event);
                eventLogRepository.save(request, submitEvent);
            }
        }
    }
}
