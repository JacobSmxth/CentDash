package com.centledger.domain;

import java.time.LocalDateTime;


public class Income extends Entry {
  public Income(LocalDateTime time, String desc, long amountCents) {
    super(EntryType.INCOME, time, desc, amountCents);
  }

  public long addToTotal(long total) {
    return total + getCents();
  }

}
