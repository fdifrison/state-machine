package com.fdifrison.dto;

public sealed interface Payload {
    record Document(String filename, byte[] file) implements Payload {}

    record ApproveCause(String approvedBy) implements Payload {}

    record RejectionCause(String rejectedBy, String cause) implements Payload {}
}
