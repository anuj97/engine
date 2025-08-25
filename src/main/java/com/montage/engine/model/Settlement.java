package com.montage.engine.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "settlements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Settlement {
    @Id
    private String id;
    private String fromUserId;
    private String toUserId;
    private Double amount;
    private String splitExpenseId;
    private String note;
}
