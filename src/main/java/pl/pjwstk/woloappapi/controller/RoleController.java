package pl.pjwstk.woloappapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.woloappapi.GlobalMapper;
import pl.pjwstk.woloappapi.model.Role;
import pl.pjwstk.woloappapi.model.dto.RoleDTO;
import pl.pjwstk.woloappapi.service.RoleService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final GlobalMapper globalMapper;

    @GetMapping()
    public ResponseEntity<List<RoleDTO>> getRoles() {
        List<Role> roles = roleService.getAllRoles();
        List<RoleDTO> roleDTOs = globalMapper.roleListToRoleDTOList(roles);
        return new ResponseEntity<>(roleDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        RoleDTO roleDTO = globalMapper.roleToRoleDTO(role);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addRole(@RequestBody RoleDTO roleDTO) {
        Role role = globalMapper.roleDTOToRole(roleDTO);
        roleService.createRole(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> editRole(@PathVariable Long id, @RequestBody RoleDTO updatedRoleDTO) {
        Role updatedRole = globalMapper.roleDTOToRole(updatedRoleDTO);
        roleService.editRole(id, updatedRole);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
