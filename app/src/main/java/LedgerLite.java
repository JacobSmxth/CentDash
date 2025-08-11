import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;



// Main Class

public class LedgerLite {
  public static void main(String[] args) {
    Ledger l = new Ledger();
    l.loadFile();

    l.addExpense(LocalDateTime.now(), "Fortnite Battlepass", 1998);
    l.addIncome(LocalDateTime.now(), "Code Ninjas", 19204);
    l.addIncome(LocalDateTime.now(), "Freelance", 29235);
    l.listEntries();
    l.writeFile();
  }
}


enum EntryType {
  INCOME,
  EXPENSE
}


// Entry Class

class Entry {
  private String desc;
  private long amountCents;
  private LocalDateTime time;
  private EntryType type;
  public Entry (EntryType type, LocalDateTime t, String desc, long amountCents) {
    this.type = type;
    this.desc = desc;
    this.time = t;
    this.amountCents = amountCents;
  }

  public String centsToString(long amountCents) {
    long abs = Math.abs(amountCents);
    return ("$"+(abs / 100)+"."+String.format("%02d", abs%100));
  }

  public EntryType getTypeEnum() {
    return type;
  }

  public String getDesc() {
    return desc;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public long getCents() {
    return amountCents;
  }

  public String getAmount() {
    return centsToString(getCents());
  }

  public String getType() {
    return type == EntryType.INCOME ? "[+]" : "[-]";
  }

  @Override
  public String toString() {
    return (getType() + " " + desc + ": (" + centsToString(amountCents)+")" + "    " + time);
  }

}


// Income Class

class Income extends Entry {
  public Income(LocalDateTime t, String desc, long amountCents) {
    super(EntryType.INCOME, t, desc, amountCents);
  }

  public long addToTotal(long total) {
    return total + getCents();
  }


}



// Expense Class


class Expense extends Entry {
  public Expense(LocalDateTime t, String desc, long amountCents) {
    super(EntryType.EXPENSE, t, desc, amountCents);
  }

  public long addToTotal(long total) {
    return total - getCents();
  }



}


// Ledger Class


class Ledger {
  private List<Entry> entries = new ArrayList<>();

  public void addExpense(LocalDateTime t, String desc, long amountCents) {
    entries.add(new Expense(t, desc, amountCents));
  }
  public void addIncome(LocalDateTime t, String desc, long amountCents) {
    entries.add(new Income(t, desc, amountCents));
  }


  public void listEntries() {
    for(Entry e: entries) {
      System.out.println(e);
    }
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

