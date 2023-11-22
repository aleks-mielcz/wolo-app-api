package pl.pjwstk.woloappapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.woloappapi.GlobalMapper;
import pl.pjwstk.woloappapi.model.User;
import pl.pjwstk.woloappapi.model.dto.UserDTO;
import pl.pjwstk.woloappapi.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final GlobalMapper globalMapper;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOs = globalMapper.userListToUserDTOList(users);
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDTO userDTO = globalMapper.userToUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addUser(@RequestBody UserDTO userDTO) {
        User user = globalMapper.userDTOToUser(userDTO);
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/shiftsCount/{id}")
    public ResponseEntity<Integer> getShiftsCountForUser(@PathVariable Long id) {
        int shiftsCount = userService.getShiftsCountForUser(id);
        return ResponseEntity.ok(shiftsCount);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> editUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        User updatedUser = globalMapper.userDTOToUser(updatedUserDTO);
        userService.editUser(id, updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
