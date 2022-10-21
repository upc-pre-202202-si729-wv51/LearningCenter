package com.acme.learningcenter.learning.service;

import com.acme.learningcenter.learning.domain.persistence.CriterionRepository;
import com.acme.learningcenter.learning.domain.persistence.SkillRepository;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class CriterionServiceImpl {

    private static final String ENTITY = "Criterion";

    private final CriterionRepository criterionRepository;

    private final Validator validator;

    private SkillRepository skillRepository;

    public CriterionServiceImpl(CriterionRepository criterionRepository, Validator validator) {
        this.criterionRepository = criterionRepository;
        this.validator = validator;
    }

}
