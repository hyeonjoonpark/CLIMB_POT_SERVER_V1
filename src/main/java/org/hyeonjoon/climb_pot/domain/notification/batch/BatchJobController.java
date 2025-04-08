package org.hyeonjoon.climb_pot.domain.notification.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BatchJobController {
    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;
}
