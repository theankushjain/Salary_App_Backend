package com.navodaya.SpecialLogin.dto;

import com.navodaya.SpecialLogin.entity.SalaryDetails.OtherAllowances;
import com.navodaya.SpecialLogin.entity.SalaryDetails.OtherDeductions;
import com.navodaya.SpecialLogin.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryRequestDTO {
    private Long userId;

    private long payLevel;

    private int year;

    private int month;

    private int daysPresent;

    private int basicPay;

    @JoinColumn(name = "allowance_id")
    @OneToMany
    private List<OtherAllowances> allowances = new ArrayList<>();

    @JoinColumn(name = "deduction_id")
    @OneToMany
    private List<OtherDeductions> deductions = new ArrayList<>();

}
