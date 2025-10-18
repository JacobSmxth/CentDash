package com.financeapi.centdash.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entry_type")
public abstract class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String desc;

    @NotNull(message = "Must have amount declared")
    private Long cents;

    private LocalDateTime time;


    public Entry() {
    }

    public Entry(String desc, Long amountCents) {
        this.desc = desc;
        this.cents = amountCents;
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
        return cents;
    }

    public void setCents(Long cents) {
        this.cents = cents;
    }

    public abstract Long addToTotal(Long total);

    public String getAmount() {
        long abs = Math.abs(cents);
        return "$" + (cents / 100) + "." + (String.format("%02d", abs % 100));
    }

    public String getType() {
        return this instanceof Income ? "INCOME" : "EXPENSE";
    }
}

