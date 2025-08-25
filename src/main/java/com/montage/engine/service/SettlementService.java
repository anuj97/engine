package com.montage.engine.service;

import com.montage.engine.model.Settlement;
import com.montage.engine.repository.SettlementRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettlementService {
  @Autowired private SettlementRepository settlementRepository;

  public Settlement recordSettlement(
      String fromUserId, String toUserId, Double amount, String splitExpenseId, String note) {
    Settlement settlement =
        new Settlement(null, fromUserId, toUserId, amount, splitExpenseId, note);
    return settlementRepository.save(settlement);
  }

  public List<Settlement> getSettlementsForUser(String userId) {
    return settlementRepository.findAll().stream()
        .filter(s -> s.getFromUserId().equals(userId) || s.getToUserId().equals(userId))
        .toList();
  }
}
