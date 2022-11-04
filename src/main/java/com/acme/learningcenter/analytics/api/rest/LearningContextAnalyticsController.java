package com.acme.learningcenter.analytics.api.rest;

import com.acme.learningcenter.analytics.domain.service.LearningContextAnalyticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics/learning")
@Tag(name = "Analytics", description = "Read business areas performance indicators")
public class LearningContextAnalyticsController {

    private final LearningContextAnalyticsService analyticsService;


    public LearningContextAnalyticsController(LearningContextAnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/skills/total")
    public int getTotalLearningSkills() {
        return analyticsService.getTotalLearningSkills();
    }
}
