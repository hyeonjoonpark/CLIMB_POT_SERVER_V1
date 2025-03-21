package org.hyeonjoon.climb_pot.domain.account.user.types;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남자"),
    FEMALE("여자");
    private final String value;

    Gender(String value) {
        this.value = value;
    }
}
