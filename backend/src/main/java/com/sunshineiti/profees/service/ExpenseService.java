package com.sunshineiti.profees.service;

import com.sunshineiti.profees.dto.ExpenseDTO;
import com.sunshineiti.profees.entity.Expense;
import com.sunshineiti.profees.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Transactional(readOnly = true)
    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ExpenseDTO createExpense(ExpenseDTO dto) {
        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setCategory(dto.getCategory());
        expense.setNote(dto.getNote());
        expense = expenseRepository.save(expense);
        return toDTO(expense);
    }

    @Transactional
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    private ExpenseDTO toDTO(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setExpenseDate(expense.getExpenseDate());
        dto.setCategory(expense.getCategory());
        dto.setNote(expense.getNote());
        return dto;
    }
}
