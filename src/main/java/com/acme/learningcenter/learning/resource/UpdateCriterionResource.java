package com.acme.learningcenter.learning.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateCriterionResource {
    private Long id;
    private Long skillId;

    @NotNull
    @NotBlank
    private String name;
}
