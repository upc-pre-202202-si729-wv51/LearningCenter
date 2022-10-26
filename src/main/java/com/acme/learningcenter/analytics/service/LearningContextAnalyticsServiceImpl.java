package com.acme.learningcenter.analytics.service;

import com.acme.learningcenter.analytics.domain.service.LearningContextAnalyticsService;
import com.acme.learningcenter.learning.api.internal.LearningContextFacade;
import org.springframework.stereotype.Service;

@Service
public class LearningContextAnalyticsServiceImpl implements LearningContextAnalyticsService {
    private final LearningContextFacade learningContextFacade;

    public LearningContextAnalyticsServiceImpl(LearningContextFacade learningContextFacade) {
        this.learningContextFacade = learningContextFacade;
    }

    @Override
    public int getTotalLearningSkills() {
        return learningContextFacade.getAllSkills().size();
    }
}
