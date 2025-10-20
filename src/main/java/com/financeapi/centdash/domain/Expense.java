package com.financeapi.centdash.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@DiscriminatorValue("EXPENSE")
public class Expense extends Entry {

    @ManyToOne
    private Budget budget;

    public Expense() {}
    public Expense(String desc, Long amountCents) {
        super(desc, amountCents);
    }

    public Long addToTotal(Long total) {
        return total - getCents();
    }


    public Budget getBudget() {
        return budget;
    }
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

}
