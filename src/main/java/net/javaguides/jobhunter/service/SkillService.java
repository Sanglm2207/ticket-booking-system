package net.javaguides.jobhunter.service;

import net.javaguides.jobhunter.domain.Skill;
import net.javaguides.jobhunter.domain.response.ResultPaginationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface SkillService {
    boolean isNameExist(String name);
    Skill fetchSkillById(long id);
    Skill createSkill(Skill s);
    Skill updateSkill(Skill s);
    void deleteSkill(long id);
    ResultPaginationDTO fetchAllSkills(Specification<Skill> spec, Pageable pageable);
}
