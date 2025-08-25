package com.montage.engine.controller;

import com.montage.engine.model.EmailVerificationToken;
import com.montage.engine.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email-verification")
public class EmailVerificationController {
  @Autowired private EmailVerificationService emailVerificationService;

  @PostMapping("/request/{userId}")
  public ResponseEntity<EmailVerificationToken> requestVerification(@PathVariable String userId) {
    EmailVerificationToken token = emailVerificationService.createTokenForUser(userId);
    // TODO: Send email with token link to user
    return ResponseEntity.ok(token);
  }

  @GetMapping("/verify")
  public ResponseEntity<String> verifyEmail(@RequestParam String token) {
    boolean success = emailVerificationService.verifyEmail(token);
    if (success) {
      return ResponseEntity.ok("Email verified successfully.");
    } else {
      return ResponseEntity.badRequest().body("Invalid or expired token.");
    }
  }
}
