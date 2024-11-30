package com.fdifrison.dto;

import com.fdifrison.service.JwtService;
import com.fdifrison.statemachine.state.State;
import java.util.UUID;

public record EventLog(UUID id, State from, State to, JwtService.Roles role, String Event, String user, String cause) {}
