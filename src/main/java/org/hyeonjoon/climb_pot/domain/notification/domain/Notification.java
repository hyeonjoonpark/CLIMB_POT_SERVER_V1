package org.hyeonjoon.climb_pot.domain.notification.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notification {
    @Id @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notification_title") private String title;
    @Column(name = "notificaton_content")  private String content;
    @Column(name = "notification_datetime") private LocalDateTime notificationDateTime;

    @Builder
    public Notification(String title, String content, LocalDateTime notificationDateTime) {
        this.title = title;
        this.content = content;
        this.notificationDateTime = notificationDateTime;
    }
}
