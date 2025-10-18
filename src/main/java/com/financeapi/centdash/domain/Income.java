package com.financeapi.centdash.domain;

import java.time.LocalDateTime;


public class Income extends Entry {
  public Income(String id, LocalDateTime time, String desc, long amountCents) {
    super(id, EntryType.INCOME, time, desc, amountCents);
  }

  public long addToTotal(long total) {
    return total + getCents();
  }

}
