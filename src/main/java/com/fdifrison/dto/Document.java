package com.fdifrison.dto;

public record Document(String filename, byte[] file) implements Payload {
}
