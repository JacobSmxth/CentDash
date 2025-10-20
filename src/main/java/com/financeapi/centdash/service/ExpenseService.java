package com.financeapi.centdash.service;

import com.financeapi.centdash.domain.Budget;
import com.financeapi.centdash.domain.Expense;
import com.financeapi.centdash.repository.BudgetRepository;
import com.financeapi.centdash.repository.EntryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ExpenseService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Transactional
    public Expense createExpense(Expense expense) {
        if (expense.getBudget() != null && expense.getBudget().getId() != null) {
            Budget budget = budgetRepository.findById(expense.getBudget().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget not found"));
            expense.setBudget(budget);
        }

        Expense saved = entryRepository.save(expense);

        if (saved.getBudget() != null) {
            Budget budget = saved.getBudget();
            budget.setCurrent(budget.getCurrent() + saved.getCents());
            budgetRepository.save(budget);
        }

        return saved;
    }

    @Transactional
    public Expense updateExpense(Long id, Expense updated) {
        Expense existing = (Expense) entryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not Found"));

        Long oldAmount = existing.getCents();
        Budget oldBudget = existing.getBudget();

        if (oldBudget != null) {
            oldBudget.setCurrent(oldBudget.getCurrent() - oldAmount);
        }

        existing.setCents(updated.getCents());
        existing.setBudget(updated.getBudget());
        existing.setDesc(updated.getDesc());

        if (existing.getBudget() != null) {
            Budget newBudget = existing.getBudget();
            newBudget.setCurrent(newBudget.getCurrent() + existing.getCents());
        }

        if(oldBudget != null) {
            budgetRepository.save(oldBudget);
        }
        if (existing.getBudget() != null) {
            budgetRepository.save(existing.getBudget());
        }

        return entryRepository.save(existing);

    }
}
