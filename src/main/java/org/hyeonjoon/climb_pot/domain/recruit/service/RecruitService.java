package org.hyeonjoon.climb_pot.domain.recruit.service;

import org.hyeonjoon.climb_pot.domain.recruit.domain.Recruit;
import org.hyeonjoon.climb_pot.domain.recruit.repository.RecruitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitService {
    
    private final RecruitRepository recruitRepository;
    private final NotificationService notificationService;
    
    @Transactional
    public Recruit addParticipant(Long recruitId, String participantId) {
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("모집 게시물을 찾을 수 없습니다."));
        
        if (recruit.isFullyRecruited()) {
            throw new IllegalStateException("이미 모집이 완료되었습니다.");
        }
        
        recruit.addParticipant(participantId);
        Recruit savedRecruit = recruitRepository.save(recruit);
        
        // 참여자에게 알림 전송
        notificationService.createRecruitParticipationNotification(participantId, recruit.getTitle());
        
        return savedRecruit;
    }
} 