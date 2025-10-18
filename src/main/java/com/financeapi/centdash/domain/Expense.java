package com.centledger.domain;

import java.time.LocalDateTime;


public class Expense extends Entry {
  public Expense(String id, LocalDateTime time, String desc, long amountCents) {
    super(id, EntryType.EXPENSE, time, desc, amountCents);
  }

  public long addToTotal(long total) {
    return total - getCents();
  }

}
