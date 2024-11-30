package com.fdifrison.repository;

import com.fdifrison.dto.EventLog;
import org.springframework.stereotype.Repository;

@Repository
public class EventLogRepository {

    public void save(EventLog request) {
        System.out.println("EventLogRepository.save()");
        System.out.println(request);
    }

}


