package com.fdifrison.dto;

import com.fdifrison.statemachine.state.StateResolver;
import java.util.UUID;

public sealed interface Request {

    record SubmitRequest(UUID id, Payload.Document payload, StateResolver.Status status) implements Request {}

    record ApproveRequest(UUID id, Payload.ApproveCause payload, StateResolver.Status status) implements Request {}

    record RejectRequest(UUID id, Payload.RejectionCause payload, StateResolver.Status status) implements Request {}
}
