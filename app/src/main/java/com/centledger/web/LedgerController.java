package com.centledger.web;

import jakarta.validation.Valid;

import com.centledger.core.LedgerService;
import com.centledger.domain.Entry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.*;

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
    return "ledger ok";
  }

  @GetMapping("/entries")
  public List<Entry> list() {
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
  @ResponseStatus(HttpStatus.CREATED)
  public Entry create(@Valid @RequestBody CreateEntry req) {
    if ("INCOME".equals(req.type().toUpperCase())) {
      return ledger.addIncome(LocalDateTime.now(), req.desc(), req.cents());
    } else {
      return ledger.addExpense(LocalDateTime.now(), req.desc(), req.cents());
    }
  }



}
