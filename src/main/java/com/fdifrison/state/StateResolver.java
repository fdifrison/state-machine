package com.fdifrison.state;

public class StateResolver {

    public enum Status {
        IN_PROGRESS,
        IN_REVIEW,
        APPROVED,
        REJECTED
    }

    public static State resolve(Status status) {
        return switch (status) {
            case IN_PROGRESS -> new InProgress();
            case IN_REVIEW -> new InReview();
            case APPROVED -> new Approved();
            case REJECTED -> new Rejected();
        };
    }

    public static Status resolve(State state) {
        return switch (state) {
            case InProgress _ -> Status.IN_PROGRESS;
            case InReview _ -> Status.IN_REVIEW;
            case Approved _ -> Status.APPROVED;
            case Rejected _ -> Status.REJECTED;

        };
    }

}
