package com.ledgerlite.core;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.ledgerlite.domain.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;

@Service
public class LedgerService {

  private List<Entry> entries = new ArrayList<>();
  private final String csvPath;

  public LedgerService(@Value("${ledger.csv.path:app/data.csv}") String csvPath) {
    this.csvPath = csvPath;
    loadFile();
  }

  public Entry addExpense(LocalDateTime t, String desc, long amountCents) {
    Entry e = new Expense(t, desc, amountCents);
    entries.add(e);
    writeFile();
    return e;
  }

  public Entry addIncome(LocalDateTime t, String desc, long amountCents) {
    Entry e = new Income(t, desc, amountCents);
    entries.add(e);
    writeFile();
    return e;
  }


  public void listEntries() {
    for(Entry e: entries) {
      System.out.println(e);
    }
  }

  public List<Entry> list() {
    return entries;
  }

  public void writeFile() {
    String csv = "data.csv";
    try (CSVWriter w = new CSVWriter(new FileWriter(csv))) {
      w.writeNext(new String[] {"Type", "Desc", "Cents", "Time"});

      for(Entry entry : entries) {
        String type = entry.getTypeEnum() == EntryType.INCOME ? "INCOME" : "EXPENSE";
        String cents = String.valueOf(entry.getCents());
        String time = entry.getTime().toString();

        w.writeNext(new String[] {type, entry.getDesc(), cents, time});
      }

      System.out.println("CSV File written");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadFile() {
    String csv = "data.csv";
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

        String type = row[0];
        String desc = row[1];
        long cents = Long.parseLong(row[2]);
        LocalDateTime time = LocalDateTime.parse(row[3]);

        if ("INCOME".equals(type)) {
          addIncome(time, desc, cents);
        } else if ("EXPENSE".equals(type)) {
          addExpense(time, desc, cents);
        }
      }
      System.out.println("CSV file loaded into memory");
    } catch (IOException | CsvValidationException ex) {
      // Ignoring as file may not exist on first run
    }
  }

 }
