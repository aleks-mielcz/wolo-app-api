package pl.pjwstk.woloappapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pjwstk.woloappapi.model.Role;

import pl.pjwstk.woloappapi.repository.RoleRepository;
import pl.pjwstk.woloappapi.utils.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("role id not found!"));
    }

    public void createRole(Role role) {
        roleRepository.save(role);
    }

    public void editRole(Long id, Role updatedRole) {
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException("Role with ID " + id + " does not exist");
        }

        Role existingRole = roleRepository.findById(id).orElseThrow();
        existingRole.setName(updatedRole.getName());

        roleRepository.save(existingRole);
    }
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new IllegalArgumentException("role with ID " + id + " does not exist");
        }
        roleRepository.deleteById(id);
    }


}