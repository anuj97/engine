package com.montage.engine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
  private String id;
  private String title;
  private long timestamp;
  private Double amount;
  private String type;
  private String account;
  private String category;
  private String tag;
}
