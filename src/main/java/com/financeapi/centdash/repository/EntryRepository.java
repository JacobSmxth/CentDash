package com.financeapi.centdash.repository;

import com.financeapi.centdash.domain.Entry;
import com.financeapi.centdash.domain.Expense;
import com.financeapi.centdash.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Query("SELECT e FROM Entry e WHERE TYPE(e) = Income")
    List<Income> findAllIncome();

    @Query("SELECT e FROM Entry e WHERE TYPE(e) = Expense")
    List<Expense> findAllExpense();
}
