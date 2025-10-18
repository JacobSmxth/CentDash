package com.financeapi.centdash.repository;

import com.financeapi.centdash.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
