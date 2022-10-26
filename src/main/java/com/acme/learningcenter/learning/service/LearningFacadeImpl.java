package com.acme.learningcenter.learning.service;

import com.acme.learningcenter.learning.api.internal.LearningFacade;
import com.acme.learningcenter.learning.domain.model.entity.Skill;
import com.acme.learningcenter.learning.domain.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningFacadeImpl implements LearningFacade {

    private final SkillService skillService;

    public LearningFacadeImpl(SkillService skillService) {
        this.skillService = skillService;
    }

    public List<Skill> getAllSkills() {
        return skillService.getAll();
    }
}
