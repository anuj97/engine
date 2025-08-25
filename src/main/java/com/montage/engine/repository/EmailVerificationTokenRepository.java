package com.montage.engine.repository;

import com.montage.engine.model.EmailVerificationToken;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailVerificationTokenRepository
    extends MongoRepository<EmailVerificationToken, String> {
  Optional<EmailVerificationToken> findByToken(String token);
}
