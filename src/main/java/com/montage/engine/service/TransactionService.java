package com.montage.engine.service;

import com.montage.engine.model.Transaction;
import com.montage.engine.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
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
