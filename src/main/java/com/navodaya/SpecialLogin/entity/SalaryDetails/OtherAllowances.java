package com.navodaya.SpecialLogin.entity.SalaryDetails;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class OtherAllowances {
    @jakarta.persistence.Id
    @GeneratedValue
    private long Id;

    private String name_of_allowance;

    private int amount_of_allowance;
}
