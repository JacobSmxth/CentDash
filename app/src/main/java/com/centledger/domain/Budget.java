package com.centledger.domain;

import java.time.LocalTime;
import java.time.LocalDateTime;

public class Budget {
  private String id;
  private String desc;
  private long maxCents;
  private long currentCents;
  private LocalDateTime lastEdit;

  public Budget(String id, String desc, long current, long maxCents, LocalDateTime lastEdit) {
    this.id = id;
    this.desc = desc;
    this.maxCents = maxCents;
    this.currentCents = current;
    this.lastEdit = lastEdit;
  }

  public long getCurrent() {
    return currentCents;
  }

  public long getMax() {
    return maxCents;
  }

  public String getUUID() {
    return id;
  }

  public String getDesc() {
    return desc;
  }

  public LocalDateTime getLastEdit() {
    return lastEdit;
  }

}
