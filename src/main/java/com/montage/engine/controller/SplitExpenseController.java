package com.montage.engine.controller;

import com.montage.engine.model.Transaction;
import com.montage.engine.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/split-expense")
public class SplitExpenseController {

  @Autowired private TransactionService transactionService;

  @PostMapping
  public ResponseEntity<Transaction> createSplitExpense(@RequestBody Transaction transaction) {
    Transaction saved = transactionService.createTransaction(transaction);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Transaction>> getExpensesForUser(@PathVariable String userId) {
    return ResponseEntity.ok(transactionService.getExpensesForUser(userId));
  }
}
