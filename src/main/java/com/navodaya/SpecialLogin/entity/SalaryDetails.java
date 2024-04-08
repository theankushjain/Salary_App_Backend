package com.navodaya.SpecialLogin.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

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

    private int HRA_percent;

    private int Transportation_Allowance;

    private boolean is_Residential_Allowance_Applicable;

    private int Residential_Allowance_Percent;

    private boolean is_NPS_Applicable;

    private boolean is_NPS_Arrear_Applicable;

    private int NPS_Arrear_Amount_Samiti_Contribution;

    private int NPS_Arrear_Own_Contibution;

    private int HM_AHM_Allowance;

    private List<OtherAllowances> otherAllowances;

    private int grossSalary;

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

    private List<> otherAllowances;

}
