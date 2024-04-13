package com.navodaya.SpecialLogin.entity.SalaryDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "other_deductions")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class OtherDeductions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String nameOfDeduction;
    private int amountOfDeduction;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "salary_details_id")
    private SalaryDetails salaryDetails;
}

