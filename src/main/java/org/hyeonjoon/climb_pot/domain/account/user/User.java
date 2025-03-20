package org.hyeonjoon.climb_pot.domain.account.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hyeonjoon.climb_pot.domain.account.user.types.Gender;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @Column(name = "user_id")
    private String id;
    @Column(nullable = false) private String name;
    @Column(nullable = false) private String nickname;
    @Column(unique = true, nullable = false) private String email;
    @Column(nullable = false) @Min(12) private int age;
    @Column(nullable = false) @Enumerated(value = EnumType.STRING) private Gender gender;
    @Column(nullable = false) private String country;
    @Column(nullable = false) private String city;

    @Builder
    public User(String name, String id, String nickname, String email, int age, Gender gender, String country, String city) {
        this.name = name;
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.country = country;
        this.city = city;
    }
}
