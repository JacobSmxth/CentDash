# Changelog

## [Unreleased]
- Turn LedgerLite into a REST API using Javalin or Spring Boot
- Add editing and deleting of entries
- Show totals and simple financial overviews
- Filter transactions by date range
- Create API endpoints for creating, reading, updating, and deleting entries
- Support other storage formats like SQLite, JSON, or a full database

## [0.1.0] - Early Build

### Day 1
- Started with the very first `LedgerLite.java` file  
- Built the `Entry` class to store a description, an amount in cents (using `long` for accuracy), and a timestamp (`LocalDateTime`)  
- Added the `Ledger` class to hold a list of entries in memory  
- Created `Income` and `Expense` classes extending `Entry` so transactions are clearly typed  
- Wrote simple methods to add income, add expenses, and print all entries to the console  

### Day 2
- Added more helper methods in `Ledger` to make working with entries cleaner  
- Tried writing a CSV manually using plain Java I/O (`FileWriter` and `BufferedReader`)  
- Hit the usual pain points with manual parsing, so switched to using a CSV library  
- Moved everything into a Gradle project to manage dependencies easily  

### Day 3
- Integrated OpenCSV for painless CSV read/write  
- Built `writeFile` to save all entries to `data.csv` with headers  
- Built `loadFile` to read `data.csv` on startup and restore previous entries  
- Added the `EntryType` enum (`INCOME`, `EXPENSE`) to get rid of string checks and make type handling cleaner  
- Updated `Income` and `Expense` constructors to automatically set their `EntryType`  
- Adjusted CSV logic to read and write using the enum instead of raw strings  

## Current Status
- Prototype works: adds sample entries, lists them in the console, saves them to CSV, and reloads them on startup  
- Still no user input  
- Editing and deleting aren’t in yet  
- No totals, summaries, or date filtering — yet