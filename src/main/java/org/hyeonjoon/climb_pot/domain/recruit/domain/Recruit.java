package org.hyeonjoon.climb_pot.domain.recruit.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hyeonjoon.climb_pot.domain.email.service.EmailService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit extends BaseTime {
    @Id
    @Column(name = "recruit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column
    private int maximum;
    @Column(nullable = false)
    private String spot;
    @Column
    private LocalDateTime meetingDate;

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

    public ResponseEntity<?> addParticipant(String participantId, EmailService emailService) {
        if (isFullyRecruited()) {
            String htmlContent = """
                <html>
                <head>
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                        .header { background-color: #4CAF50; color: white; padding: 15px; text-align: center; border-radius: 5px; }
                        .content { padding: 20px; background-color: #f9f9f9; border-radius: 5px; margin-top: 20px; }
                        .info-item { margin-bottom: 10px; }
                        .info-label { font-weight: bold; color: #555; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="content">
                            <p>안녕하세요, CLIMB POT 입니다.</p>
                            <p>귀하가 작성하신 모집글의 정원이 초과되었습니다.</p>
                            <p>추가모집자 신청이 있습니다</p>
                            <div class="info-item">
                                <span class="info-label">제목:</span> %s
                            </div>
                            <div class="info-item">
                                <span class="info-label">장소:</span> %s
                            </div>
                            <div class="info-item">
                                <span class="info-label">모임 날짜:</span> %s
                            </div>
                            <div class="info-item">
                                <span class="info-label">현재 참가자 수:</span> %d명
                            </div>
                            <div class="info-item">
                                <span class="info-label">최대 인원:</span> %d명
                            </div>
                            <div class="info-item">
                                <span class="info-label">추가모집자:</span> %s
                            </div>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(
                    this.title, 
                    this.spot, 
                    this.meetingDate.toString(), 
                    this.participantIds.size(), 
                    this.maximum,
                    SecurityContextHolder.getContext().getAuthentication().getName()
                );
                
            emailService.sendEmail(
                this.getCreatedBy(),
                "[CLIMB POT] 모집 인원 초과 알림",
                htmlContent
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(htmlContent);
        }
        participantIds.add(participantId);
        return ResponseEntity.status(200).body("성공적으로 모집신청을 하였습니다");
    }

    public boolean isFullyRecruited() {
        return this.participantIds.size() == this.maximum;
    }
}
