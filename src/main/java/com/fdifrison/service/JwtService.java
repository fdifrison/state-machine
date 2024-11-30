package com.fdifrison.service;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public enum Roles {
        ADMIN,
        USER,
    }

    private Roles role = Roles.USER;

    // simulate roles inside a jwt in the security context

    public Roles getUserRole() {
        return role;
    }

    public void setUserRole(Roles role) {
        System.out.println("Logging as : " + role);
        this.role = role;
    }
}
