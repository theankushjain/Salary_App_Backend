package com.navodaya.SpecialLogin.entity.SalaryDetails;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class OtherDeductions {
    @Id
    @GeneratedValue
    private long Id;

    private String name_of_deduction;

    private int amount_of_deduction;
}

