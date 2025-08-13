package com.ledgerlite.domain;

import java.time.LocalDateTime;


public class Expense extends Entry {
  public Expense(LocalDateTime time, String desc, long amountCents) {
    super(EntryType.EXPENSE, time, desc, amountCents);
  }

  public long addToTotal(long total) {
    return total - getCents();
  }

}
