package com.acme.learningcenter.analytics.service;

import com.acme.learningcenter.analytics.domain.service.EducationContextAnalyticsService;
import com.acme.learningcenter.learning.api.internal.LearningFacade;
import org.springframework.stereotype.Service;

@Service
public class EducationContextAnalyticsServiceImpl implements EducationContextAnalyticsService {
    private final LearningFacade learningFacade;

    public EducationContextAnalyticsServiceImpl(LearningFacade learningFacade) {
        this.learningFacade = learningFacade;
    }

    @Override
    public int getTotalSkills() {
        return learningFacade.getAllSkills().size();
    }
}
