package com.financeapi.centdash.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;


@Entity
@DiscriminatorValue("EXPENSE")
public class Expense extends Entry {
  public Expense(Long id, LocalDateTime time, String desc, Long amountCents) {
    super(id, EntryType.EXPENSE, time, desc, amountCents);
  }

  public Long addToTotal(Long total) {
    return total - getCents();
  }

}
