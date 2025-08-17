package com.centledger.core;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.centledger.domain.*;
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
  private List<Budget> budgets = new ArrayList<>();
  private final String csvPath;

  public LedgerService(@Value("${ledger.csv.path:app/data.csv}") String csvPath) {
    this.csvPath = csvPath;
    loadEntries();
    loadBudgets();
  }

  public Entry addExpense(LocalDateTime t, String desc, long amountCents) {
    Entry e = new Expense(t, desc, amountCents);
    entries.add(e);
    writeEntries();
    return e;
  }

  public Entry addIncome(LocalDateTime t, String desc, long amountCents) {
    Entry e = new Income(t, desc, amountCents);
    entries.add(e);
    writeEntries();
    return e;
  }

  public Budget addBudget(String id, String desc, long current, long maxCents, LocalDateTime lastEdit) {
    Budget budget = new Budget(String.format("%s", java.util.UUID.randomUUID()), desc, current, maxCents, lastEdit);
    budgets.add(budget);
    writeBudgets();
    return budget;
  }

  public List<Entry> listEntries() {
    return entries;
  }

  public List<Budget> listBudgets() {
    return budgets;
  }

  public void writeEntries() {
    String csv = "entries.csv";
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

  public void writeBudgets() {
    String csv = "budgets.csv";
    try (CSVWriter w = new CSVWriter(new FileWriter(csv))) {
      w.writeNext(new String[] {"ID", "Desc", "Current", "Max", "LastEdit"});

      for(Budget budget : budgets) {
        String currentCents = String.valueOf(budget.getCurrent());
        String maxCents = String.valueOf(budget.getMax());
        String lastEdit = budget.getLastEdit().toString();

        w.writeNext(new String[] {budget.getUUID(), budget.getDesc(), currentCents, maxCents, lastEdit});
      }

      System.out.println("CSV File written");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadBudgets() {
    String csv = "budgets.csv";
    try (CSVReader r = new CSVReader(new FileReader(csv))) {
      String[] row;
      boolean first = true; // Used to skip header
      while ((row = r.readNext()) != null) {
        if (first) {
          first = false;
          continue;
        }
        if (row.length < 5) {
          continue;
        }

        String id = row[0];
        String desc = row[1];
        long currentCents = Long.parseLong(row[2]);
        long maxCents = Long.parseLong(row[3]);
        LocalDateTime time = LocalDateTime.parse(row[4]);

        addBudget(id, desc, currentCents, maxCents, time);
       }
      System.out.println("CSV file loaded into memory");
    } catch (IOException | CsvValidationException ex) {
      // Ignoring as file may not exist on first run
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
