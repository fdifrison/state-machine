package com.fdifrison.dto;

public sealed interface Payload permits RejectionCause, Document {
}
