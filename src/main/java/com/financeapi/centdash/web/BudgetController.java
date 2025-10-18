package com.financeapi.centdash.web;

import com.financeapi.centdash.repository.BudgetRepository;

import com.financeapi.centdash.domain.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;


    @GetMapping
    public List<Budget> listAllBudgets() {
        return budgetRepository.findAll();
    }


    @PostMapping
    public ResponseEntity<Budget> create(@RequestBody Budget budget) {
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetRepository.save(budget));
    }

}
