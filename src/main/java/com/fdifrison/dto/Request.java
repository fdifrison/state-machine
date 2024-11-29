package com.fdifrison.dto;

import com.fdifrison.state.State;
import com.fdifrison.state.StateResolver;

import java.util.UUID;

public record Request(UUID id, Payload payload, StateResolver.Status status) {
}
