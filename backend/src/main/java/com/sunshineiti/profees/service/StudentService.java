package com.sunshineiti.profees.service;

import com.sunshineiti.profees.dto.PaymentDTO;
import com.sunshineiti.profees.dto.StudentDTO;
import com.sunshineiti.profees.entity.Payment;
import com.sunshineiti.profees.entity.Student;
import com.sunshineiti.profees.repository.PaymentRepository;
import com.sunshineiti.profees.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PaymentRepository paymentRepository;

    public StudentService(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAllOrderByCreatedAtDesc().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return toDTO(student);
    }

    @Transactional
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = new Student();
        updateEntity(student, dto);
        student = studentRepository.save(student);
        return toDTO(student);
    }

    @Transactional
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        updateEntity(student, dto);
        student = studentRepository.save(student);
        return toDTO(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> searchStudents(String query) {
        return studentRepository.findByNameContainingIgnoreCase(query).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private void updateEntity(Student student, StudentDTO dto) {
        student.setName(dto.getName());
        student.setFatherName(dto.getFatherName());
        student.setAppNo(dto.getAppNo());
        student.setTrade(dto.getTrade());
        student.setAdmissionYear(dto.getAdmissionYear());
        student.setTotalFee(dto.getTotalFee());
        student.setAadhaar(dto.getAadhaar());
        student.setMobile(dto.getMobile());
        student.setSamagra(dto.getSamagra());
        student.setCaste(dto.getCaste());
        student.setIncome(dto.getIncome());
        student.setAddress(dto.getAddress());
        student.setPhotoUrl(dto.getPhotoUrl());
        student.setDocumentUrl(dto.getDocumentUrl());
    }

    public StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setFatherName(student.getFatherName());
        dto.setAppNo(student.getAppNo());
        dto.setTrade(student.getTrade());
        dto.setAdmissionYear(student.getAdmissionYear());
        dto.setTotalFee(student.getTotalFee());
        dto.setAadhaar(student.getAadhaar());
        dto.setMobile(student.getMobile());
        dto.setSamagra(student.getSamagra());
        dto.setCaste(student.getCaste());
        dto.setIncome(student.getIncome());
        dto.setAddress(student.getAddress());
        dto.setPhotoUrl(student.getPhotoUrl());
        dto.setDocumentUrl(student.getDocumentUrl());

        BigDecimal paid = paymentRepository.getTotalPaidByStudentId(student.getId());
        dto.setPaidAmount(paid);
        BigDecimal totalFee = student.getTotalFee() != null ? student.getTotalFee() : BigDecimal.ZERO;
        dto.setBalance(totalFee.subtract(paid));

        List<PaymentDTO> paymentDTOs = student.getPayments().stream()
                .map(this::toPaymentDTO)
                .collect(Collectors.toList());
        dto.setPayments(paymentDTOs);

        return dto;
    }

    private PaymentDTO toPaymentDTO(Payment payment) {
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
