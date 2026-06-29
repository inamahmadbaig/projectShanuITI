package com.sunshineiti.profees.service;

import com.sunshineiti.profees.dto.PaymentDTO;
import com.sunshineiti.profees.entity.Payment;
import com.sunshineiti.profees.entity.Student;
import com.sunshineiti.profees.repository.PaymentRepository;
import com.sunshineiti.profees.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public PaymentDTO createPayment(PaymentDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMode(dto.getPaymentMode());
        payment.setRemark(dto.getRemark());

        payment = paymentRepository.save(payment);
        return toDTO(payment);
    }

    @Transactional
    public PaymentDTO updatePayment(Long id, PaymentDTO dto) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMode(dto.getPaymentMode());
        payment.setRemark(dto.getRemark());

        payment = paymentRepository.save(payment);
        return toDTO(payment);
    }

    @Transactional
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByStudentId(Long studentId) {
        return paymentRepository.findByStudentIdOrderByPaymentDateDesc(studentId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PaymentDTO toDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setStudentId(payment.getStudent().getId());
        dto.setStudentName(payment.getStudent().getName());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setPaymentMode(payment.getPaymentMode());
        dto.setRemark(payment.getRemark());
        return dto;
    }
}
