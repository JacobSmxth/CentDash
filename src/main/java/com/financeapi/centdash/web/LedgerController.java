package com.financeapi.centdash.web;

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

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/entries")
public class LedgerController {

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping("/health")
    public String health() {
        return "ledger ok";
    }

    @GetMapping
    public List<Entry> listAllEntries() {
        return entryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Entry> create(@RequestBody Entry entry) {
        return ResponseEntity.ok(entryRepository.save(entry));
    }

}
