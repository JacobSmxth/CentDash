package com.centledger.web;

import com.centledger.core.BudgetService;
import com.centledger.domain.Budget;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class BudgetController {
  private final BudgetService budget;
  public BudgetController(BudgetService budget) {
    this.budget = budget;
  }

  @GetMapping("/budgets")
  public HashMap<String, Budget> list() {
    return budget.list();
  }

  record CreateBudget(String desc, long current, long max) {}

  @PostMapping("/budgets")
  @ResponseStatus(HttpStatus.CREATED)
  public Budget create(@RequestBody CreateBudget req) {
    return budget.addBudget(String.format("%s", java.util.UUID.randomUUID()), req.desc(), req.current(), req.max(), LocalDateTime.now());
  }

}
