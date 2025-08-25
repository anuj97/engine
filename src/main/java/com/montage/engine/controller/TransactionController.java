package com.montage.engine.controller;

import com.montage.engine.dto.TransactionDTO;
import com.montage.engine.dto.TransactionRequestDTO;
import com.montage.engine.model.Transaction;
import com.montage.engine.service.TransactionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
  @PostMapping("/split")
  public ResponseEntity<TransactionDTO> splitTransaction(@RequestBody TransactionRequestDTO req) {
    Transaction transaction = fromRequestDTO(req);
    Transaction split = transactionService.splitTransaction(transaction);
    return ResponseEntity.ok(toDTO(split));
  }

  @Autowired private TransactionService transactionService;

  @GetMapping
  public List<TransactionDTO> getAllTransactions() {
    return transactionService.getAllTransactions().stream().map(this::toDTO).toList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable String id) {
    Optional<Transaction> transaction = transactionService.getTransactionById(id);
    return transaction
        .map(t -> ResponseEntity.ok(toDTO(t)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public TransactionDTO createTransaction(@RequestBody TransactionRequestDTO req) {
    Transaction transaction = fromRequestDTO(req);
    Transaction created = transactionService.createTransaction(transaction);
    return toDTO(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TransactionDTO> updateTransaction(
      @PathVariable String id, @RequestBody TransactionRequestDTO req) {
    Transaction transaction = fromRequestDTO(req);
    Transaction updated = transactionService.updateTransaction(id, transaction);
    return ResponseEntity.ok(toDTO(updated));
  }

  private Transaction fromRequestDTO(TransactionRequestDTO req) {
    Transaction t = new Transaction();
    java.time.Instant instant = java.time.Instant.ofEpochMilli(req.getTimestamp());
    java.time.ZonedDateTime zdt = instant.atZone(java.time.ZoneId.systemDefault());
    t.setTitle(req.getTitle());
    t.setDate(zdt.toLocalDate());
    t.setTime(zdt.toLocalTime());
    t.setAmount(req.getAmount());
    t.setType(
        req.getType() != null
            ? com.montage.engine.model.TransactionType.valueOf(req.getType())
            : null);
    t.setAccount(req.getAccount());
    t.setCategory(req.getCategory());
    t.setTag(req.getTag());
    return t;
  }

  private TransactionDTO toDTO(Transaction t) {
    long timestamp = 0;
    if (t.getDate() != null && t.getTime() != null) {
      timestamp =
          t.getDate()
              .atTime(t.getTime())
              .atZone(java.time.ZoneId.systemDefault())
              .toInstant()
              .toEpochMilli();
    }
    return new TransactionDTO(
        t.getId(),
        t.getTitle(),
        timestamp,
        t.getAmount(),
        t.getType() != null ? t.getType().name() : null,
        t.getAccount(),
        t.getCategory(),
        t.getTag());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTransaction(@PathVariable String id) {
    transactionService.deleteTransaction(id);
    return ResponseEntity.noContent().build();
  }
}
