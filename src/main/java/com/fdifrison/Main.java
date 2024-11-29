package com.fdifrison;

import com.fdifrison.controller.StateController;
import com.fdifrison.dto.Document;
import com.fdifrison.dto.Request;
import com.fdifrison.repository.EventLogRepository;
import com.fdifrison.service.EventLogService;
import com.fdifrison.service.StateService;
import com.fdifrison.state.StateResolver;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        var controller = new StateController(new StateService(new EventLogService(new EventLogRepository())));

        var request = new Request(
                UUID.randomUUID(),
                new Document("MyDoc", new byte[1000]),
                StateResolver.Status.IN_PROGRESS);

        var submit = controller.Submit(request);

        System.out.println("Final state of submit operation is: " + StateResolver.resolve(submit));

    }


}