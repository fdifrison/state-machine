package com.fdifrison.dto;

import com.fdifrison.state.StateResolver;

import java.util.UUID;

public sealed interface Request permits Request.SubmitRequest, Request.ApproveRequest, Request.RejectRequest {

    record SubmitRequest(UUID id, Payload.Document payload, StateResolver.Status status) implements Request {
    }

    record ApproveRequest(UUID id, Payload.ApproveCause payload, StateResolver.Status status) implements Request {
    }

    record RejectRequest(UUID id, Payload.RejectionCause payload, StateResolver.Status status) implements Request {
    }
}
