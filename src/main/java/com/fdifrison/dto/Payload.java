package com.fdifrison.dto;

public sealed interface Payload permits Payload.ApproveCause, Payload.Document, Payload.RejectionCause {
    record Document(String filename, byte[] file) implements Payload {
    }

    record ApproveCause(String approvedBy) implements Payload {
    }

    record RejectionCause(String rejectedBy, String cause) implements Payload {
    }
}
