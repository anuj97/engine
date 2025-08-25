package com.montage.engine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {
    private String title;
    private long timestamp;
    private Double amount;
    private String type;
    private String account;
    private String category;
    private String tag;
}
