package com.financeapi.centdash.web;

import jakarta.validation.Valid;

import com.financeapi.centdash.core.LedgerService;
import com.financeapi.centdash.domain.Entry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.HashMap;

import java.net.URI;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class LedgerController {
  private final LedgerService ledger;
  public LedgerController(LedgerService ledger) {
    this.ledger = ledger;
  }

  @GetMapping("/health")
  public String health() {
    return "ledger ok";
  }

  @GetMapping("/entries")
  public HashMap<String, Entry> list() {
    return ledger.list();
  }

  record CreateEntry(
      @Pattern(regexp = "^(INCOME|EXPENSE)$", message = "Type must be INCOME or EXPENSE")
      String type,
      @NotBlank(message = "Description can't be blank")
      @Size(min = 1, max = 255, message= "Description must be between 1 and 255 characters")
      String desc,
      @Min(value = 0, message ="Can't enter a negative value for cents")
      long cents
  ) {}

  @PostMapping("/entries")
  public ResponseEntity<Entry> create(@Valid @RequestBody CreateEntry req) {
    String uuid = java.util.UUID.randomUUID().toString();
    Entry newEntry;
    if ("INCOME".equals(req.type().toUpperCase())) {
      newEntry = ledger.addIncome(uuid, LocalDateTime.now(), req.desc(), req.cents());
    } else {
      newEntry = ledger.addExpense(uuid, LocalDateTime.now(), req.desc(), req.cents());
    }

    return ResponseEntity.created(URI.create("/api/entries/" + newEntry.getUUID()))
      .body(newEntry);
  }

}
