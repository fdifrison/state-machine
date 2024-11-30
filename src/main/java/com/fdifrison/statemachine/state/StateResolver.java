package com.fdifrison.statemachine.state;

public class StateResolver {

    public enum Status {
        IN_PROGRESS,
        IN_REVIEW,
        APPROVED,
        REJECTED
    }

    public static State resolve(Status status) {
        return switch (status) {
            case IN_PROGRESS -> new State.InProgress(Status.IN_PROGRESS);
            case IN_REVIEW -> new State.InReview(Status.IN_REVIEW);
            case APPROVED -> new State.Approved(Status.APPROVED);
            case REJECTED -> new State.Rejected(Status.REJECTED);
        };
    }

    public static Status resolve(State state) {
        return switch (state) {
            case State.InProgress _ -> Status.IN_PROGRESS;
            case State.InReview _ -> Status.IN_REVIEW;
            case State.Approved _ -> Status.APPROVED;
            case State.Rejected _ -> Status.REJECTED;

        };
    }

}
