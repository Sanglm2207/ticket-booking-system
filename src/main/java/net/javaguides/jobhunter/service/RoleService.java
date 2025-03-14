package net.javaguides.jobhunter.service;

import net.javaguides.jobhunter.domain.Role;
import net.javaguides.jobhunter.domain.response.ResultPaginationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface RoleService {
    boolean existByName(String name);
    Role create(Role r);
    Role fetchById(long id);
    Role update(Role r);
    void delete(long id);
    ResultPaginationDTO getRoles(Specification<Role> spec, Pageable pageable);
}
