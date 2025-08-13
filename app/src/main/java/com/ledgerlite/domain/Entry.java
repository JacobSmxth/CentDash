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
}
