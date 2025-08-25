package com.montage.engine.service;

import com.montage.engine.model.EmailVerificationToken;
import com.montage.engine.model.User;
import com.montage.engine.repository.EmailVerificationTokenRepository;
import com.montage.engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmailVerificationService {
    @Autowired
    private EmailVerificationTokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;

    public EmailVerificationToken createTokenForUser(String userId) {
        String token = UUID.randomUUID().toString();
        long now = System.currentTimeMillis();
        long expiresAt = now + 24 * 60 * 60 * 1000; // 24 hours
        EmailVerificationToken verificationToken = new EmailVerificationToken(null, userId, token, now, expiresAt, false);
        return tokenRepository.save(verificationToken);
    }

    public boolean verifyEmail(String token) {
        Optional<EmailVerificationToken> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isPresent()) {
            EmailVerificationToken verificationToken = tokenOpt.get();
            if (!verificationToken.isUsed() && verificationToken.getExpiresAt() > System.currentTimeMillis()) {
                Optional<User> userOpt = userRepository.findById(verificationToken.getUserId());
                if (userOpt.isPresent()) {
                    User user = userOpt.get();
                    user.setEmailVerified(true);
                    userRepository.save(user);
                    verificationToken.setUsed(true);
                    tokenRepository.save(verificationToken);
                    return true;
                }
            }
        }
        return false;
    }
}
