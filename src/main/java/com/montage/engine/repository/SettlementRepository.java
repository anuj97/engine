package com.montage.engine.repository;

import com.montage.engine.model.Settlement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SettlementRepository extends MongoRepository<Settlement, String> {
}
