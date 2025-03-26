package org.hyeonjoon.climb_pot.domain.notification.repository;

import org.hyeonjoon.climb_pot.domain.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

} 