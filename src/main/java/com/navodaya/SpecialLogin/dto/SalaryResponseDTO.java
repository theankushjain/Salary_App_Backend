package com.navodaya.SpecialLogin.dto;

import com.navodaya.SpecialLogin.entity.SalaryDetails.OtherAllowances;
import com.navodaya.SpecialLogin.entity.SalaryDetails.OtherDeductions;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SalaryResponseDTO {
    private Long id;
    private long payLevel;
    private int year;
    private int month;
    private int noOfDaysPresent;
    private int basicPay;
    private List<OtherAllowances> otherAllowances;
    private int totalAmountAllowances;
    private List<OtherDeductions> otherDeductions;
    private int totalAmountDeductions;
    private int grossSalary;
    private int netSalary;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
