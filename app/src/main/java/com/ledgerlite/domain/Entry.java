package com.ledgerlite.domain;

import java.time.LocalDateTime;

public abstract class Entry { // Just found out abstract makes it more of a blueprint
  private String desc;
  private long amountCents;
  private LocalDateTime time;
  private EntryType type;

  public Entry (EntryType type, LocalDateTime time, String desc, long amountCents) {
    this.type =type;
    this.desc = desc;
    this.time = time;
    this.amountCents = amountCents;
  }

  public EntryType getTypeEnum() {
    return type;
  }

  public String getDesc() {
    return desc;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public long getCents() {
    return amountCents;
  }

  public String getAmount() {
    long abs = Math.abs(amountCents);
    return "$" + (amountCents / 100) + "." + (String.format("%02d", abs % 100));
  }

  public String getType() {
    return type == EntryType.INCOME ? "INCOME" : "EXPENSE";
  }
}

