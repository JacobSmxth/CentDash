package com.centledger.web;

import com.centledger.core.LedgerService;
import com.centledger.domain.Entry;
import com.centledger.domain.Budget;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LedgerController {
  private final LedgerService ledger;
  public LedgerController(LedgerService ledger) {
    this.ledger = ledger;
  }

  @GetMapping("/health")
  public String health() {
    return "ok";
  }

  @GetMapping("/entries")
  public List<Entry> listEntries() {
    return ledger.listEntries();
  }

  @GetMapping("/budgets")
  public List<Budget> listBudgets() {
    return ledger.listBudgets();
  }

  record CreateEntry(String type, String desc, long cents) {}

  @PostMapping("/entries")
  @ResponseStatus(HttpStatus.CREATED)
  public Entry create(@RequestBody CreateEntry req) {
    String t = req.type() == null ? "" : req.type().toUpperCase();
    if ("INCOME".equals(t)) {
      return ledger.addIncome(LocalDateTime.now(), req.desc(), req.cents());
    }
    if ("EXPENSE".equals(t)) {
      return ledger.addExpense(LocalDateTime.now(), req.desc(), req.cents());
    }
    throw new IllegalArgumentException("type must be INCOME or EXPENSE");
  }


  record CreateBudget(String desc, long current, long max) {}

  @PostMapping("/budgets")
  @ResponseStatus(HttpStatus.CREATED)
  public Budget create(@RequestBody CreateBudget req) {
    return ledger.addBudget(String.format("%s", java.util.UUID.randomUUID()), req.desc(), req.current(), req.max(), LocalDateTime.now());
  }


}
