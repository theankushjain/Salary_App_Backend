package com.navodaya.SpecialLogin.entity.SalaryDetails;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name="salary_details")
public class SalaryDetails {
    @Id
    @GeneratedValue
    private long Id;

    private long pay_level;

    private int year;

    private int month;

    private int no_of_days_present;

    private int basic_pay;

    private int dearness_allowance_percent;

    private int hra_percent;

    private int transportation_allowance;

    private boolean is_residential_allowance_applicable;

    private int residential_allowance_percent;

    private boolean is_nps_applicable;

    private boolean is_nps_arrear_applicable;

    private int nps_arrear_amount_samiti_contribution;

    private int nps_arrear_own_contibution;

    private int hm_ahm_allowance;

    @JoinColumn(name = "allowance_id")
    @OneToMany
    private List<OtherAllowances> other_allowances = new ArrayList<>();

    private int gross_salary;

    private int cpf_regular_subscription;

    private int cpf_arrear;

    private int recovery_advance;

    private int gslis_amount;

    private int ngtis_contibution;

    private int recovery_of_vehicle_advance;

    private int income_tax;

    private int water_electricity;

    private int licence_fees;

    private int professional_tax;

    private int audit_recovery;

    private List<OtherDeductions> other_deductions = new ArrayList<>();
}
