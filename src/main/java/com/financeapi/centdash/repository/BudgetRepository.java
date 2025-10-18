package com.financeapi.centdash.repository;

import com.financeapi.centdash.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
