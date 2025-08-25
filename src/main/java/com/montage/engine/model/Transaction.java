package com.montage.engine.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    private String id;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private Double amount;
    private TransactionType type;
    private String account;
    private String category;
    private String tag;
}
