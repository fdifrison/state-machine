package com.fdifrison.statemachine.state;

public sealed interface State {

    record Approved(StateResolver.Status approved) implements State {
        @Override
        public String toString() {
            return StateResolver.resolve(this).toString();
        }
    }

    record InReview(StateResolver.Status inReview) implements State {
        @Override
        public String toString() {
            return StateResolver.resolve(this).toString();
        }
    }

    record Rejected(StateResolver.Status rejected) implements State {
        @Override
        public String toString() {
            return StateResolver.resolve(this).toString();
        }
    }

    record InProgress(StateResolver.Status status) implements State {
        @Override
        public String toString() {
            return StateResolver.resolve(this).toString();
        }
    }
}
