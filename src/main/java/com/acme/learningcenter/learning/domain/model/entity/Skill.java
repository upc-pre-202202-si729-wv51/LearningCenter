package com.acme.learningcenter.learning.domain.model.entity;

import com.acme.learningcenter.shared.domain.model.AuditModel;
import com.acme.learningcenter.shared.exception.ResourceNotFoundException;
import com.acme.learningcenter.shared.exception.ResourceValidationException;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "skills")
public class Skill extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull
    @NotBlank
    @Size(max = 60)
    private String name;

    // Relationships

    @OneToMany(cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    mappedBy = "skill")
    private Set<Criterion> criteria = new HashSet<>();

    public Skill addCriterion(String criterionName) {

        // Initialize if null

        if (criteria == null)
            criteria = new HashSet<>();

        // Criterion Name uniqueness validation under skill scope
        if(!criteria.isEmpty())
            if(criteria.stream().anyMatch(criterion ->
                    criterion.getName().equals(criterionName)))
                throw new ResourceValidationException("Criterion",
                        "A criterion with the same name already exists for skill.");

        // Add Criterion to Skill
        criteria.add(new Criterion()
                .withName(criterionName)
                .withSkill(this));
        return this;
    }


}
