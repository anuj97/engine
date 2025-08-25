package com.montage.engine.repository;

import com.montage.engine.model.EmailVerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface EmailVerificationTokenRepository extends MongoRepository<EmailVerificationToken, String> {
    Optional<EmailVerificationToken> findByToken(String token);
}
