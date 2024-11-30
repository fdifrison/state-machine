package com.fdifrison;

import com.fdifrison.dto.Payload;
import com.fdifrison.dto.Request;
import com.fdifrison.service.JwtService;
import com.fdifrison.statemachine.StateMachineService;
import com.fdifrison.statemachine.event.ApproveEvent;
import com.fdifrison.statemachine.event.RejectEvent;
import com.fdifrison.statemachine.event.SubmitEvent;
import com.fdifrison.statemachine.state.State;
import com.fdifrison.statemachine.state.StateResolver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner (StateMachineService service, JwtService jwtService) {
        return args -> {
            var submit = new Request.SubmitRequest(
                    UUID.randomUUID(),
                    new Payload.Document("MyDoc", new byte[1000]));
            service.handleEvent(new SubmitEvent(new State.InProgress(StateResolver.Status.IN_PROGRESS), submit));

            System.out.println("\n---------------------------------\n");

            var approve = new Request.ApproveRequest(
                    UUID.randomUUID(),
                    new Payload.ApproveCause("G.F"));


            try {
                service.handleEvent( new ApproveEvent(new State.InReview(StateResolver.Status.IN_REVIEW), approve));
            } catch (Exception e) {
                System.out.println("\nEXCEPTION  ----------------------");
                System.out.println(e.getMessage());
                System.out.println("EXCEPTION  ----------------------\n");
                jwtService.setUserRole(JwtService.Roles.ADMIN);
                service.handleEvent( new ApproveEvent(new State.InReview(StateResolver.Status.IN_REVIEW), approve));
            }

            System.out.println("\n---------------------------------\n");


            var reject = new Request.RejectRequest(
                    UUID.randomUUID(),
                    new Payload.RejectionCause("C.N", "Rejected because you like python"));

            service.handleEvent( new RejectEvent(new State.InReview(StateResolver.Status.IN_REVIEW), reject));

            System.out.println("\n---------------------------------\n");

        };
    }

}