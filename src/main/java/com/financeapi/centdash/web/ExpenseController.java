package com.financeapi.centdash.web;


import com.financeapi.centdash.domain.Expense;
import com.financeapi.centdash.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping
    public List<Expense> getExpenses() {
        return entryRepository.findAllExpenses();
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        return ResponseEntity.ok(entryRepository.save(expense));
    }
}
