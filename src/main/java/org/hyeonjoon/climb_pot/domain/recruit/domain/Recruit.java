package org.hyeonjoon.climb_pot.domain.recruit.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hyeonjoon.climb_pot.glabal.domain.BaseTime;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    
    @ElementCollection
    @CollectionTable(
        name = "recruit_participants",
        joinColumns = @JoinColumn(name = "recruit_id")
    )
    @Column(name = "participant_id")
    private List<String> participantIds = new ArrayList<>();

    @Builder
    public Recruit(String title, String content, int maximum, String spot, LocalDateTime meetingDate) {
        this.title = title;
        this.content = content;
        this.maximum = maximum;
        this.spot = spot;
        this.meetingDate = meetingDate;
        this.participantIds = new ArrayList<>();
    }

    public void addParticipant(String participantId) {
        if (this.participantIds.size() >= this.maximum) {
            throw new IllegalStateException("모집 인원이 초과되었습니다.");
        }
        this.participantIds.add(participantId);
    }

    public boolean isFullyRecruited() {
        return this.participantIds.size() >= this.maximum;
    }
}
