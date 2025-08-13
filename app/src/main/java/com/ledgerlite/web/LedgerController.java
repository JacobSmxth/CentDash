package com.ledgerlite.web;

import com.ledgerlite.core.LedgerService;
import com.ledgerlite.domain.Entry;
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


}
