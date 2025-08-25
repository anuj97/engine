package com.montage.engine.controller;

import com.montage.engine.dto.SettlementRequestDTO;
import com.montage.engine.model.Settlement;
import com.montage.engine.service.SettlementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settlements")
public class SettlementController {
  @Autowired private SettlementService settlementService;

  @PostMapping
  public ResponseEntity<Settlement> recordSettlement(@RequestBody SettlementRequestDTO request) {
    Settlement settlement =
        settlementService.recordSettlement(
            request.getFromUserId(),
            request.getToUserId(),
            request.getAmount(),
            request.getSplitExpenseId(),
            request.getNote());
    return ResponseEntity.ok(settlement);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Settlement>> getSettlementsForUser(@PathVariable String userId) {
    return ResponseEntity.ok(settlementService.getSettlementsForUser(userId));
  }
}
