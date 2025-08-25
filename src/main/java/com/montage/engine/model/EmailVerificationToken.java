package com.montage.engine.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "email_verification_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerificationToken {
    @Id
    private String id;
    private String userId;
    private String token;
    private long createdAt;
    private long expiresAt;
    private boolean used;
}
