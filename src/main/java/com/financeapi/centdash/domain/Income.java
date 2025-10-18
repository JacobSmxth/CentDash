package com.financeapi.centdash.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("INCOME")
public class Income extends Entry {
  public Income(Long id, LocalDateTime time, String desc, Long amountCents) {
    super(id, EntryType.INCOME, time, desc, amountCents);
  }

  public Long addToTotal(Long total) {
    return total + getCents();
  }

}
