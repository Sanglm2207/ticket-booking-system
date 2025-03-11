package net.javaguides.concertticket.controller;

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
import net.javaguides.concertticket.domain.Role;
import net.javaguides.concertticket.domain.response.ResultPaginationDTO;
import net.javaguides.concertticket.service.Impl.RoleServiceImpl;
import net.javaguides.concertticket.util.annotation.ApiMessage;
import net.javaguides.concertticket.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

    private final RoleServiceImpl roleServiceImpl;

    public RoleController(RoleServiceImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
    }

    @PostMapping("/roles")
    @ApiMessage("Create a role")
    public ResponseEntity<Role> create(@Valid @RequestBody Role r) throws IdInvalidException {
        // check name
        if (this.roleServiceImpl.existByName(r.getName())) {
            throw new IdInvalidException("Role với name = " + r.getName() + " đã tồn tại");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.roleServiceImpl.create(r));
    }

    @PutMapping("/roles")
    @ApiMessage("Update a role")
    public ResponseEntity<Role> update(@Valid @RequestBody Role r) throws IdInvalidException {
        // check id
        if (this.roleServiceImpl.fetchById(r.getId()) == null) {
            throw new IdInvalidException("Role với id = " + r.getId() + " không tồn tại");
        }

        // check name
        // if (this.roleService.existByName(r.getName())) {
        // throw new IdInvalidException("Role với name = " + r.getName() + " đã tồn
        // tại");
        // }

        return ResponseEntity.ok().body(this.roleServiceImpl.update(r));
    }

    @DeleteMapping("/roles/{id}")
    @ApiMessage("Delete a role")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) throws IdInvalidException {
        // check id
        if (this.roleServiceImpl.fetchById(id) == null) {
            throw new IdInvalidException("Role với id = " + id + " không tồn tại");
        }
        this.roleServiceImpl.delete(id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/roles")
    @ApiMessage("Fetch roles")
    public ResponseEntity<ResultPaginationDTO> getPermissions(
            @Filter Specification<Role> spec, Pageable pageable) {

        return ResponseEntity.ok(this.roleServiceImpl.getRoles(spec, pageable));
    }

    @GetMapping("/roles/{id}")
    @ApiMessage("Fetch role by id")
    public ResponseEntity<Role> getById(@PathVariable("id") long id) throws IdInvalidException {

        Role role = this.roleServiceImpl.fetchById(id);
        if (role == null) {
            throw new IdInvalidException("Resume với id = " + id + " không tồn tại");
        }

        return ResponseEntity.ok().body(role);
    }

}
