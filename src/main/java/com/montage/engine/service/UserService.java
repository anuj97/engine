package com.montage.engine.service;

import com.montage.engine.model.User;
import com.montage.engine.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  public java.util.List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(String id) {
    return userRepository.findById(id).orElse(null);
  }

  public User createUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User updateUser(String id, User user) {
    Optional<User> existing = userRepository.findById(id);
    if (existing.isPresent()) {
      User toUpdate = existing.get();
      toUpdate.setUsername(user.getUsername());
      toUpdate.setEmail(user.getEmail());
      toUpdate.setFirstName(user.getFirstName());
      toUpdate.setLastName(user.getLastName());
      toUpdate.setPhoneNumber(user.getPhoneNumber());
      toUpdate.setProfilePictureUrl(user.getProfilePictureUrl());
      toUpdate.setDefaultCurrency(user.getDefaultCurrency());
      toUpdate.setEmailVerified(user.isEmailVerified());
      if (user.getPassword() != null && !user.getPassword().isEmpty()) {
        toUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
      }
      return userRepository.save(toUpdate);
    }
    return null;
  }

  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }

  @Autowired private UserRepository userRepository;

  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public User signUp(String username, String password) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("Username already exists");
    }
    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    return userRepository.save(user);
  }

  public Optional<User> login(String username, String password) {
    Optional<User> userOpt = userRepository.findByUsername(username);
    if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
      return userOpt;
    }
    return Optional.empty();
  }
}
