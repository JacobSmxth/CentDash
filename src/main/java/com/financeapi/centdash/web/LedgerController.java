package com.financeapi.centdash.web;

import com.financeapi.centdash.domain.Expense;
import com.financeapi.centdash.domain.Income;
import com.financeapi.centdash.repository.EntryRepository;
import jakarta.validation.Valid;

import com.financeapi.centdash.domain.Entry;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.HashMap;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/entries")
public class LedgerController {

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping
    public List<Entry> listAllEntries() {
        return entryRepository.findAll();

    }

    @GetMapping("/net-total")
    public ResponseEntity<HashMap<String, Object>> getNetTotal() {
        HashMap<String, Object> information = new HashMap<>();


        Long incomeTotal = entryRepository.findAllIncomes().stream()
                .mapToLong(Income::getCents)
                .sum();
        Long expenseTotal = entryRepository.findAllExpenses().stream()
                .mapToLong(Expense::getCents)
                .sum();

        long netTotal = incomeTotal - expenseTotal;


        long dollars = netTotal / 100;
        long cents = Math.abs(netTotal % 100);
        String formattedTotal = "$%d.%02d".formatted(dollars, cents);

        information.put("incomeTotalCents", incomeTotal);
        information.put("expenseTotalCents", expenseTotal);
        information.put("netTotalCents", netTotal);
        information.put("doubleTotalDollars", netTotal / 100.0);
        information.put("formattedTotal", formattedTotal);

        return ResponseEntity.ok(information);
    }

}
