package com.financeapi.centdash.web;

import com.financeapi.centdash.domain.Income;
import com.financeapi.centdash.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping
    public List<Income> getIncomes() {
        return entryRepository.findAllIncome();
    }

    @PostMapping
    public ResponseEntity<Income> createIncome(@RequestBody Income income) {
        return ResponseEntity.ok(entryRepository.save(income));
    }
}
