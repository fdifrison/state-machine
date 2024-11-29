package com.fdifrison.event;

public sealed interface UserEvent extends Event permits  SubmitEvent {
    @Override
    default void guardEvent() {
        System.out.println("Applying the User Event Guard");
    }
}
