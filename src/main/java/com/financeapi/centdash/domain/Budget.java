package com.financeapi.centdash.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Budget {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String desc;
    @NotNull(message = "Can't have the maxCents be null")
    private Long maxCents;

    @NotNull(message = "Can't have the currentCents be null")
    private Long currentCents;


    private LocalDateTime lastEdit;

    public Budget() {
    }

    public Budget(String desc, Long current, Long maxCents) {
        this.desc = desc;
        this.maxCents = maxCents;
        this.currentCents = current;
    }


    public long getCurrent() {
        return currentCents;
    }

    public void setCurrent(long currentCents) {
        this.currentCents = currentCents;
    }

    public long getMax() {
        return maxCents;
    }

    public void setMax(long maxCents) {
        this.maxCents = maxCents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @PrePersist
    public void prePersist() {
        this.lastEdit = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastEdit = LocalDateTime.now();
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(LocalDateTime lastEdit) {
        this.lastEdit = lastEdit;
    }

}
