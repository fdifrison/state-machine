package com.fdifrison;

import com.fdifrison.controller.StateController;
import com.fdifrison.dto.Payload;
import com.fdifrison.dto.Request;
import com.fdifrison.repository.EventLogRepository;
import com.fdifrison.service.EventLogService;
import com.fdifrison.service.JwtService;
import com.fdifrison.service.StateMachineService;
import com.fdifrison.state.StateResolver;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        var eventLogRepository = new EventLogRepository();
        var jwtService = new JwtService();
        var eventLogService = new EventLogService(jwtService, eventLogRepository);
        var guardService = new JwtService();
        var stateService = new StateMachineService(eventLogService, guardService);
        var controller = new StateController(stateService);

        System.out.println("\n---------------------------------\n");

        var submit = new Request.SubmitRequest(
                UUID.randomUUID(),
                new Payload.Document("MyDoc", new byte[1000]),
                StateResolver.Status.IN_PROGRESS);

        var submitResponse = (Request.SubmitRequest) controller.Submit(submit);

        System.out.println("\n---------------------------------\n");

        var approve = new Request.ApproveRequest(
                submitResponse.id(),
                new Payload.ApproveCause("G.F"),
                submitResponse.status());

        Request.ApproveRequest approvedResponse;

        try {
            approvedResponse = (Request.ApproveRequest) controller.Approve(approve);
        } catch (Exception e) {
            System.out.println("\nEXCEPTION  ----------------------");
            System.out.println(e.getMessage());
            System.out.println("EXCEPTION  ----------------------\n");
            guardService.setUserRole(JwtService.Roles.ADMIN);
            approvedResponse = (Request.ApproveRequest) controller.Approve(approve);
        }

        System.out.println("\n---------------------------------\n");


        var reject = new Request.RejectRequest(
                approvedResponse.id(),
                new Payload.RejectionCause("C.N", "Rejected because you like python"),
                StateResolver.Status.IN_REVIEW);

        var rejectResponse = controller.Reject(reject);

        System.out.println("\n---------------------------------\n");

    }


}