package com.sunshineiti.profees.dto;

import java.math.BigDecimal;

public class DashboardDTO {
    private long totalStudents;
    private BigDecimal totalCollected;
    private BigDecimal totalPending;
    private BigDecimal totalExpenses;
    private BigDecimal netBalance;

    public long getTotalStudents() { return totalStudents; }
    public void setTotalStudents(long totalStudents) { this.totalStudents = totalStudents; }
    public BigDecimal getTotalCollected() { return totalCollected; }
    public void setTotalCollected(BigDecimal totalCollected) { this.totalCollected = totalCollected; }
    public BigDecimal getTotalPending() { return totalPending; }
    public void setTotalPending(BigDecimal totalPending) { this.totalPending = totalPending; }
    public BigDecimal getTotalExpenses() { return totalExpenses; }
    public void setTotalExpenses(BigDecimal totalExpenses) { this.totalExpenses = totalExpenses; }
    public BigDecimal getNetBalance() { return netBalance; }
    public void setNetBalance(BigDecimal netBalance) { this.netBalance = netBalance; }
}
