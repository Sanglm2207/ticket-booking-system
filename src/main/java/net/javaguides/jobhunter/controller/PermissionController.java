package net.javaguides.jobhunter.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import net.javaguides.jobhunter.domain.Permission;
import net.javaguides.jobhunter.domain.response.ResultPaginationDTO;
import net.javaguides.jobhunter.service.Impl.PermissionServiceImpl;
import net.javaguides.jobhunter.util.annotation.ApiMessage;
import net.javaguides.jobhunter.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1")
public class PermissionController {

    private final PermissionServiceImpl permissionServiceImpl;

    public PermissionController(PermissionServiceImpl permissionServiceImpl) {
        this.permissionServiceImpl = permissionServiceImpl;
    }

    @PostMapping("/permissions")
    @ApiMessage("Create a permission")
    public ResponseEntity<Permission> create(@Valid @RequestBody Permission p) throws IdInvalidException {
        // check exist
        if (this.permissionServiceImpl.isPermissionExist(p)) {
            throw new IdInvalidException("Permission đã tồn tại.");
        }

        // create new permission
        return ResponseEntity.status(HttpStatus.CREATED).body(this.permissionServiceImpl.create(p));
    }

    @PutMapping("/permissions")
    @ApiMessage("Update a permission")
    public ResponseEntity<Permission> update(@Valid @RequestBody Permission p) throws IdInvalidException {
        // check exist by id
        if (this.permissionServiceImpl.fetchById(p.getId()) == null) {
            throw new IdInvalidException("Permission với id = " + p.getId() + " không tồn tại.");
        }

        // check exist by module, apiPath and method
        if (this.permissionServiceImpl.isPermissionExist(p)) {
            // check name
            if (this.permissionServiceImpl.isSameName(p)) {
                throw new IdInvalidException("Permission đã tồn tại.");
            }
        }

        // update permission
        return ResponseEntity.ok().body(this.permissionServiceImpl.update(p));
    }

    @DeleteMapping("/permissions/{id}")
    @ApiMessage("delete a permission")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) throws IdInvalidException {
        // check exist by id
        if (this.permissionServiceImpl.fetchById(id) == null) {
            throw new IdInvalidException("Permission với id = " + id + " không tồn tại.");
        }
        this.permissionServiceImpl.delete(id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/permissions")
    @ApiMessage("Fetch permissions")
    public ResponseEntity<ResultPaginationDTO> getPermissions(
            @Filter Specification<Permission> spec, Pageable pageable) {

        return ResponseEntity.ok(this.permissionServiceImpl.getPermissions(spec, pageable));
    }
}
