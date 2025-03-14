package net.javaguides.jobhunter.service;

import net.javaguides.jobhunter.domain.Permission;
import net.javaguides.jobhunter.domain.response.ResultPaginationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PermissionService {
    boolean isPermissionExist(Permission p);
    Permission fetchById(long id);
    Permission create(Permission p);
    Permission update(Permission p);
    void delete(long id);
    ResultPaginationDTO getPermissions(Specification<Permission> spec, Pageable pageable);
    boolean isSameName(Permission p);
}
