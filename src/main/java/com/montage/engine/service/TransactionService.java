package com.montage.engine.service;

import com.montage.engine.model.Transaction;
import com.montage.engine.repository.TransactionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  public Transaction splitTransaction(Transaction transaction) {
    // Example split logic: distribute amount among participants
    if (transaction.getParticipantUserIds() != null
        && !transaction.getParticipantUserIds().isEmpty()) {
      double splitAmount = transaction.getAmount() / transaction.getParticipantUserIds().size();
      java.util.Map<String, Double> userShares = new java.util.HashMap<>();
      for (String userId : transaction.getParticipantUserIds()) {
        userShares.put(userId, splitAmount);
      }
      transaction.setUserShares(userShares);
    }
    return transactionRepository.save(transaction);
  }

  public List<Transaction> getExpensesForUser(String userId) {
    return transactionRepository.findAll().stream()
        .filter(
            t -> t.getParticipantUserIds() != null && t.getParticipantUserIds().contains(userId))
        .toList();
  }

  @Autowired private TransactionRepository transactionRepository;

  public List<Transaction> getAllTransactions() {
    return transactionRepository.findAll();
  }

  public Optional<Transaction> getTransactionById(String id) {
    return transactionRepository.findById(id);
  }

  public Transaction createTransaction(Transaction transaction) {
    if (transaction.getParticipantUserIds() != null
        && !transaction.getParticipantUserIds().isEmpty()) {
      return splitTransaction(transaction);
    }
    return transactionRepository.save(transaction);
  }

  public Transaction updateTransaction(String id, Transaction transaction) {
    transaction.setId(id);
    return transactionRepository.save(transaction);
  }

  public void deleteTransaction(String id) {
    transactionRepository.deleteById(id);
  }
}
