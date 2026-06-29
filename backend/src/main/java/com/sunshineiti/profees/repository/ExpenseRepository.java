package com.sunshineiti.profees.repository;

import com.sunshineiti.profees.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e")
    BigDecimal getTotalExpenses();

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.expenseDate BETWEEN :start AND :end")
    BigDecimal getTotalExpensesBetween(LocalDate start, LocalDate end);
}
