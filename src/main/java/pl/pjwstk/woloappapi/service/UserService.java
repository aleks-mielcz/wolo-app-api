package pl.pjwstk.woloappapi.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pjwstk.woloappapi.model.User;
import pl.pjwstk.woloappapi.model.Role;

import pl.pjwstk.woloappapi.repository.RoleRepository;
import pl.pjwstk.woloappapi.repository.UserRepository;
import pl.pjwstk.woloappapi.utils.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User id not found!"));
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User updateUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " does not exist");
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist");
        }
        userRepository.deleteById(id);
    }

    public List<User> getByRole(Long role) {
        Optional<Role> roleById = roleRepository.findById(role);
        if(roleById.isEmpty()){
            throw new IllegalArgumentException("Role does not exist");
        }
        return userRepository.getUsersByRole(roleById);
    }
    public int getShiftsCountForUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            return user.getShifts().size();
        } else {
            return 0; // lub można zwrócić odpowiedni kod błędu
        }
    }
    public void editUser(Long id, User updatedUser) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User with ID " + id + " does not exist");
        }

        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setFirstname(updatedUser.getFirstname());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setRole(updatedUser.getRole());
        existingUser.setPeselVerified(updatedUser.isPeselVerified());
        existingUser.setAgreementSigned(updatedUser.isAgreementSigned());
        existingUser.setAdult(updatedUser.isAdult());
        existingUser.setOrganization(updatedUser.getOrganization());
        existingUser.setShifts(updatedUser.getShifts());
        existingUser.setPassword_hash(updatedUser.getPassword_hash());
        existingUser.setSalt(updatedUser.getSalt());

        userRepository.save(existingUser);
    }
}
