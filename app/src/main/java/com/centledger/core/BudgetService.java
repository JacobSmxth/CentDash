package com.centledger.core;

import org.springframework.stereotype.Service;

import com.centledger.domain.*;
import java.util.HashMap;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.time.LocalDateTime;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;

@Service
public class BudgetService {
  private HashMap<String, Budget> budgets = new HashMap<>();

  public BudgetService() {
    loadBudgets();
  }

  public Budget addBudget(String id, String desc, long current, long maxCents, LocalDateTime lastEdit) {
    String uuid = String.format("%s", java.util.UUID.randomUUID());
    Budget budget = new Budget(uuid, desc, current, maxCents, lastEdit);
    budgets.put(uuid, budget);
    writeBudgets();
    return budget;
  }


  public HashMap<String, Budget> list() {
    return budgets;
  }


  public void writeBudgets() {
    String csv = "budgets.csv";
    try (CSVWriter w = new CSVWriter(new FileWriter(csv))) {
      w.writeNext(new String[] {"ID", "Desc", "Current", "Max", "LastEdit"});

      for(Budget budget : budgets.values()) {
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

}


