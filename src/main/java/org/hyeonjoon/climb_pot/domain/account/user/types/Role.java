package org.hyeonjoon.climb_pot.domain.account.user.types;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");
    private final String name;

    Role(String name) {
        this.name = name;
    }
}
