package com.sunshineiti.profees.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class StudentDTO {
    private Long id;
    @NotBlank
    private String name;
    private String fatherName;
    private String appNo;
    private String trade;
    private String admissionYear;
    @NotNull
    private BigDecimal totalFee;
    private BigDecimal paidAmount;
    private BigDecimal balance;
    private String aadhaar;
    private String mobile;
    private String samagra;
    private String caste;
    private String income;
    private String address;
    private List<PaymentDTO> payments;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    public String getAppNo() { return appNo; }
    public void setAppNo(String appNo) { this.appNo = appNo; }
    public String getTrade() { return trade; }
    public void setTrade(String trade) { this.trade = trade; }
    public String getAdmissionYear() { return admissionYear; }
    public void setAdmissionYear(String admissionYear) { this.admissionYear = admissionYear; }
    public BigDecimal getTotalFee() { return totalFee; }
    public void setTotalFee(BigDecimal totalFee) { this.totalFee = totalFee; }
    public BigDecimal getPaidAmount() { return paidAmount; }
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public String getAadhaar() { return aadhaar; }
    public void setAadhaar(String aadhaar) { this.aadhaar = aadhaar; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getSamagra() { return samagra; }
    public void setSamagra(String samagra) { this.samagra = samagra; }
    public String getCaste() { return caste; }
    public void setCaste(String caste) { this.caste = caste; }
    public String getIncome() { return income; }
    public void setIncome(String income) { this.income = income; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public List<PaymentDTO> getPayments() { return payments; }
    public void setPayments(List<PaymentDTO> payments) { this.payments = payments; }
}
