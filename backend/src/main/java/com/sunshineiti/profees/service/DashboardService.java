package com.sunshineiti.profees.service;

import com.sunshineiti.profees.dto.DashboardDTO;
import com.sunshineiti.profees.entity.Student;
import com.sunshineiti.profees.repository.ExpenseRepository;
import com.sunshineiti.profees.repository.PaymentRepository;
import com.sunshineiti.profees.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class DashboardService {

    private final StudentRepository studentRepository;
    private final PaymentRepository paymentRepository;
    private final ExpenseRepository expenseRepository;

    public DashboardService(StudentRepository studentRepository,
                            PaymentRepository paymentRepository,
                            ExpenseRepository expenseRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.expenseRepository = expenseRepository;
    }

    @Transactional(readOnly = true)
    public DashboardDTO getDashboard() {
        DashboardDTO dto = new DashboardDTO();

        dto.setTotalStudents(studentRepository.count());

        BigDecimal totalCollected = paymentRepository.getTotalCollected();
        dto.setTotalCollected(totalCollected);

        BigDecimal totalFees = studentRepository.findAll().stream()
                .map(s -> s.getTotalFee() != null ? s.getTotalFee() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotalPending(totalFees.subtract(totalCollected));

        BigDecimal totalExpenses = expenseRepository.getTotalExpenses();
        dto.setTotalExpenses(totalExpenses);

        dto.setNetBalance(totalCollected.subtract(totalExpenses));

        return dto;
    }
}
