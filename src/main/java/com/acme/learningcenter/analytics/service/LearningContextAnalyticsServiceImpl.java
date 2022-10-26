package com.acme.learningcenter.analytics.service;

import com.acme.learningcenter.analytics.domain.service.LearningContextAnalyticsService;
import com.acme.learningcenter.learning.api.internal.LearningFacade;
import org.springframework.stereotype.Service;

@Service
public class LearningContextAnalyticsServiceImpl implements LearningContextAnalyticsService {
    private final LearningFacade learningFacade;

    public LearningContextAnalyticsServiceImpl(LearningFacade learningFacade) {
        this.learningFacade = learningFacade;
    }

    @Override
    public int getTotalLearningSkills() {
        return learningFacade.getAllSkills().size();
    }
}
