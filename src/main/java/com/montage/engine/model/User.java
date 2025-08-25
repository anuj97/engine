package com.montage.engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id private String id;
  private String username;
  private String password;
  private String email;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String profilePictureUrl;
  private String defaultCurrency;
  private boolean emailVerified;
  @CreatedDate private long createdAt;
  @LastModifiedDate private long updatedAt;
}
