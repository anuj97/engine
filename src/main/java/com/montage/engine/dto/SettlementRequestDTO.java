package com.montage.engine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementRequestDTO {
  private String fromUserId;
  private String toUserId;
  private Double amount;
  private String splitExpenseId;
  private String note;
}
