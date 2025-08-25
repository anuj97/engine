package com.montage.engine.controller;

import com.montage.engine.model.Settlement;
import com.montage.engine.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settlements")
public class SettlementController {
    @Autowired
    private SettlementService settlementService;

    @PostMapping
    public ResponseEntity<Settlement> recordSettlement(@RequestBody SettlementRequest request) {
        Settlement settlement = settlementService.recordSettlement(
                request.getFromUserId(),
                request.getToUserId(),
                request.getAmount(),
                request.getSplitExpenseId(),
                request.getNote()
        );
        return ResponseEntity.ok(settlement);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Settlement>> getSettlementsForUser(@PathVariable String userId) {
        return ResponseEntity.ok(settlementService.getSettlementsForUser(userId));
    }

    public static class SettlementRequest {
        private String fromUserId;
        private String toUserId;
        private Double amount;
        private String splitExpenseId;
        private String note;
        public String getFromUserId() { return fromUserId; }
        public void setFromUserId(String fromUserId) { this.fromUserId = fromUserId; }
        public String getToUserId() { return toUserId; }
        public void setToUserId(String toUserId) { this.toUserId = toUserId; }
        public Double getAmount() { return amount; }
        public void setAmount(Double amount) { this.amount = amount; }
        public String getSplitExpenseId() { return splitExpenseId; }
        public void setSplitExpenseId(String splitExpenseId) { this.splitExpenseId = splitExpenseId; }
        public String getNote() { return note; }
        public void setNote(String note) { this.note = note; }
    }
}
