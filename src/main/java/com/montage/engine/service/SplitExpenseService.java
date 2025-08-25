package com.montage.engine.service;

import com.montage.engine.model.SplitExpense;
import com.montage.engine.repository.SplitExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SplitExpenseService {
    @Autowired
    private SplitExpenseRepository splitExpenseRepository;

    public SplitExpense createSplitExpense(String title, Double totalAmount, String paidByUserId, List<String> participantUserIds, String category, String tag) {
        Map<String, Double> userShares = new HashMap<>();
        double share = totalAmount / participantUserIds.size();
        for (String userId : participantUserIds) {
            userShares.put(userId, share);
        }
        SplitExpense expense = new SplitExpense(null, title, totalAmount, paidByUserId, participantUserIds, userShares, null, category, tag);
        return splitExpenseRepository.save(expense);
    }

    public List<SplitExpense> getExpensesForUser(String userId) {
        // This is a simple example, you may want to add custom queries for more advanced filtering
        return splitExpenseRepository.findAll().stream()
                .filter(e -> e.getParticipantUserIds().contains(userId) || e.getPaidByUserId().equals(userId))
                .toList();
    }
}
