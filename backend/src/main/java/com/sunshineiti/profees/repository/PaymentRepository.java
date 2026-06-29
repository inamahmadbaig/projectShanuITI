package com.sunshineiti.profees.repository;

import com.sunshineiti.profees.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentIdOrderByPaymentDateDesc(Long studentId);

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.student.id = :studentId")
    BigDecimal getTotalPaidByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p")
    BigDecimal getTotalCollected();

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.paymentDate BETWEEN :start AND :end")
    BigDecimal getTotalCollectedBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
