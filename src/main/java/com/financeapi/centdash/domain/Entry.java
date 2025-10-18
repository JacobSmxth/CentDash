package com.centledger.domain;

import java.time.LocalDateTime;

public abstract class Entry { // Just found out abstract makes it more of a blueprint
  private String id;
  private String desc;
  private long amountCents;
  private LocalDateTime time;
  private EntryType type;

  public Entry (String id, EntryType type, LocalDateTime time, String desc, long amountCents) {
    this.id = id;
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

  public String getUUID() {
    return id;
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

