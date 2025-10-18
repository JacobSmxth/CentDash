package com.financeapi.centdash.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public abstract class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String desc;

    @NotNull(message = "Must have amount declared")
    private Long amountCents;

    private LocalDateTime time;

    private EntryType type;

    public Entry(EntryType type, LocalDateTime time, String desc, Long amountCents) {
        this.type = type;
        this.desc = desc;
        this.amountCents = amountCents;
    }

    public EntryType getTypeEnum() {
        return type;
    }
    public void setTypeEnum(EntryType type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist() {
        this.time = LocalDateTime.now();
    }

    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getCents() {
        return amountCents;
    }
    public void setCents(Long cents) {
        this.amountCents = cents;
    }

    public abstract Long addToTotal(Long total);

    public String getAmount() {
        long abs = Math.abs(amountCents);
        return "$" + (amountCents / 100) + "." + (String.format("%02d", abs % 100));
    }

    public String getType() {
        return type == EntryType.INCOME ? "INCOME" : "EXPENSE";
    }
}

