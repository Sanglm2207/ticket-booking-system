package net.javaguides.jobhunter.repository;

import java.util.List;

import net.javaguides.jobhunter.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>,
                JpaSpecificationExecutor<Skill> {

        boolean existsByName(String name);

        List<Skill> findByIdIn(List<Long> id);
}
