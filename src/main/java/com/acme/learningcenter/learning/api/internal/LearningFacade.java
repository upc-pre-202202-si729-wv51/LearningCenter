package com.acme.learningcenter.learning.api.internal;

import com.acme.learningcenter.learning.domain.model.entity.Skill;

import java.util.List;

public interface LearningFacade {
    List<Skill> getAllSkills();
}
