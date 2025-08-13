package com.centledger.web;

import com.centledger.core.LedgerService;
import com.centledger.domain.Entry;
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
  public List<Entry> list() {
    return ledger.list();
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


}
