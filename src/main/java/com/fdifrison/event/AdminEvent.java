package com.fdifrison.event;

public sealed interface AdminEvent extends Event permits ApproveEvent, RejectEvent {

    @Override
    default void guardEvent() {
        System.out.println("Applying the Admin Event Guard");
    }
}
