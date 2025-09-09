package com.centledger.core;

import org.springframework.stereotype.Service;

import com.centledger.domain.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;

@Service
public class LedgerService {

  private HashMap<String, Entry> entries = new HashMap<>();

  public LedgerService() {
    loadEntries();
  }

  public Entry addExpense(String id, LocalDateTime t, String desc, long amountCents) {
    Entry e = new Expense(id, t, desc, amountCents);
    entries.put(id, e);
    writeEntries();
    return e;
  }

  public Entry addIncome(String id, LocalDateTime t, String desc, long amountCents) {
    Entry e = new Income(id, t, desc, amountCents);
    entries.put(id, e);
    writeEntries();
    return e;
  }

  public HashMap<String, Entry> list() {
    return entries;
  }

  public void writeEntries() {
    String csv = "entries.csv";
    try (CSVWriter w = new CSVWriter(new FileWriter(csv))) {
      w.writeNext(new String[] {"ID", "Type", "Desc", "Cents", "Time"});

      for(Entry entry : entries.values()) {
        String type = entry.getTypeEnum() == EntryType.INCOME ? "INCOME" : "EXPENSE";
        String cents = String.valueOf(entry.getCents());
        String time = entry.getTime().toString();

        w.writeNext(new String[] {entry.getUUID(), type, entry.getDesc(), cents, time});
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public void loadEntries() {
    String csv = "entries.csv";
    try (CSVReader r = new CSVReader(new FileReader(csv))) {
      String[] row;
      boolean first = true; // Used to skip header
      while ((row = r.readNext()) != null) {
        if (first) {
          first = false;
          continue;
        }
        if (row.length < 4) {
          continue;
        }

        String uuid = row[0];
        String type = row[1];
        String desc = row[2];
        long cents = Long.parseLong(row[3]);
        LocalDateTime time = LocalDateTime.parse(row[4]);

        if ("INCOME".equals(type)) {
          addIncome(uuid,time, desc, cents);
        } else if ("EXPENSE".equals(type)) {
          addExpense(uuid, time, desc, cents);
        }
      }
      System.out.println("CSV file loaded into memory");
    } catch (IOException | CsvValidationException ex) {
      // Ignoring as file may not exist on first run
    }
  }

 }
