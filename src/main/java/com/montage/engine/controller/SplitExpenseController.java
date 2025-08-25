package com.montage.engine.controller;

import com.montage.engine.model.SplitExpense;
import com.montage.engine.service.SplitExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/split-expense")
public class SplitExpenseController {
    @Autowired
    private SplitExpenseService splitExpenseService;

    @PostMapping
    public ResponseEntity<SplitExpense> createSplitExpense(@RequestBody SplitExpenseRequest request) {
        SplitExpense expense = splitExpenseService.createSplitExpense(
                request.getTitle(),
                request.getTotalAmount(),
                request.getPaidByUserId(),
                request.getParticipantUserIds(),
                request.getCategory(),
                request.getTag()
        );
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SplitExpense>> getExpensesForUser(@PathVariable String userId) {
        return ResponseEntity.ok(splitExpenseService.getExpensesForUser(userId));
    }

    public static class SplitExpenseRequest {
        private String title;
        private Double totalAmount;
        private String paidByUserId;
        private List<String> participantUserIds;
        private String category;
        private String tag;
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public Double getTotalAmount() { return totalAmount; }
        public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
        public String getPaidByUserId() { return paidByUserId; }
        public void setPaidByUserId(String paidByUserId) { this.paidByUserId = paidByUserId; }
        public List<String> getParticipantUserIds() { return participantUserIds; }
        public void setParticipantUserIds(List<String> participantUserIds) { this.participantUserIds = participantUserIds; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public String getTag() { return tag; }
        public void setTag(String tag) { this.tag = tag; }
    }
}
