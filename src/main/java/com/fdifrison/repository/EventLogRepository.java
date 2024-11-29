package com.fdifrison.repository;

import com.fdifrison.dto.Request;
import com.fdifrison.event.*;

public class EventLogRepository {

    public void save(Request request, Event event) {
        System.out.println("Saving event to Auditing table:");
        System.out.println(event);
        System.out.println(request);
    }

}


