package com.fdifrison.dto;

import com.fdifrison.statemachine.state.StateResolver;

import java.util.Objects;
import java.util.UUID;

public sealed interface Request {

    record SubmitRequest(UUID id, Payload.Document payload) implements Request {
        public SubmitRequest {
            Objects.requireNonNull(id, "id cannot be null");
        }
    }

    record ApproveRequest(UUID id, Payload.ApproveCause payload) implements Request {}

    record RejectRequest(UUID id, Payload.RejectionCause payload) implements Request {}
}
