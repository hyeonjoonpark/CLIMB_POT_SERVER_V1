package org.hyeonjoon.climb_pot.domain.recruit.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hyeonjoon.climb_pot.glabal.domain.BaseTime;

import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit extends BaseTime {
    @Id @Column(name = "recruit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String title;
    @Column(nullable = false) private String content;
    @Column private int maximum;
    @Column(nullable = false) private String spot;
    @Column private LocalDateTime meetingDate;

    @Builder
    public Recruit(String title, String content, int maximum, String spot, LocalDateTime meetingDate) {
        this.title = title;
        this.content = content;
        this.maximum = maximum;
        this.spot = spot;
        this.meetingDate = meetingDate;
    }
}
