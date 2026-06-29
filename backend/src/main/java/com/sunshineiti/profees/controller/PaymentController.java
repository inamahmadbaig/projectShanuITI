package com.sunshineiti.profees.controller;

import com.sunshineiti.profees.dto.PaymentDTO;
import com.sunshineiti.profees.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(paymentService.getPaymentsByStudentId(studentId));
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO dto) {
        return ResponseEntity.ok(paymentService.createPayment(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @RequestBody PaymentDTO dto) {
        return ResponseEntity.ok(paymentService.updatePayment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }
}
