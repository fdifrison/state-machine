package com.fdifrison.event;

import com.fdifrison.dto.Request;
import com.fdifrison.service.EventLogService;
import com.fdifrison.service.JwtService;

public sealed interface Event permits Event.UserEvent, Event.AdminEvent {

    void logEvent(EventLogService logService);

    void guard(JwtService jwtService);

    Request handleEvent();

    sealed interface UserEvent extends Event permits SubmitEvent {
    }

    sealed interface AdminEvent extends Event permits ApproveEvent, RejectEvent {
        @Override
        default void guard(JwtService jwtService) {
            if (!jwtService.getUserRole().equals(JwtService.Roles.ADMIN)) {
                throw new IllegalArgumentException("Admin event invoked with role: " + jwtService.getUserRole());
            }
            System.out.println("Guard passed for Admin Event");
        };
    }

}

