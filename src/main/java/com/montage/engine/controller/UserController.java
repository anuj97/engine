package com.montage.engine.controller;

import com.montage.engine.dto.UserRequestDTO;
import com.montage.engine.dto.UserResponseDTO;
import com.montage.engine.model.User;
import com.montage.engine.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    List<UserResponseDTO> dtos = users.stream().map(this::toDTO).toList();
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
    User user = userService.getUserById(id);
    if (user != null) {
      return ResponseEntity.ok(toDTO(user));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO req) {
    User created = userService.createUser(fromRequestDTO(req));
    return ResponseEntity.ok(toDTO(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> updateUser(
      @PathVariable String id, @RequestBody UserRequestDTO req) {
    User updated = userService.updateUser(id, fromRequestDTO(req));
    if (updated != null) {
      return ResponseEntity.ok(toDTO(updated));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  private User fromRequestDTO(UserRequestDTO req) {
    User user = new User();
    user.setUsername(req.getUsername());
    user.setPassword(req.getPassword());
    user.setEmail(req.getEmail());
    user.setFirstName(req.getFirstName());
    user.setLastName(req.getLastName());
    user.setPhoneNumber(req.getPhoneNumber());
    user.setProfilePictureUrl(req.getProfilePictureUrl());
    user.setDefaultCurrency(req.getDefaultCurrency());
    return user;
  }

  private UserResponseDTO toDTO(User user) {
    return new UserResponseDTO(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getFirstName(),
        user.getLastName(),
        user.getPhoneNumber(),
        user.getProfilePictureUrl(),
        user.getDefaultCurrency(),
        user.isEmailVerified(),
        user.getCreatedAt(),
        user.getUpdatedAt());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
