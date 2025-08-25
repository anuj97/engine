package com.montage.engine.repository;

import com.montage.engine.model.SplitExpense;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SplitExpenseRepository extends MongoRepository<SplitExpense, String> {
}
