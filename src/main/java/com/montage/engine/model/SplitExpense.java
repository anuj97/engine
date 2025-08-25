package com.montage.engine.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@Document(collection = "split_expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SplitExpense {
    @Id
    private String id;
    private String title;
    private Double totalAmount;
    private String paidByUserId;
    private List<String> participantUserIds;
    private Map<String, Double> userShares; // userId -> amount owed
    private String groupId; // optional, for group expenses
    private String category;
    private String tag;
}
