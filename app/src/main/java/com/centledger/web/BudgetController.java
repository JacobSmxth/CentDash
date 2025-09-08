package com.centledger.web;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

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


  record CreateBudget(
      @NotBlank(message = "Description can't be blank")
      @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
      String desc,
      @Min(value = 0, message = "Current amount must be positive")
      long current,
      @Min(value = 0, message = "Max amount must be positive")
      long max
  ) {

  }

  @PostMapping("/budgets")
  @ResponseStatus(HttpStatus.CREATED)
  public Budget create(@Valid @RequestBody CreateBudget req) {
    return budget.addBudget(java.util.UUID.randomUUID().toString(), req.desc(), req.current(), req.max(), LocalDateTime.now());
  }

}
