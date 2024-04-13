package com.navodaya.SpecialLogin.entity.SalaryDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "other_allowances")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class OtherAllowances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String nameOfAllowance;
    private int amountOfAllowance;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "salary_details_id")
    private SalaryDetails salaryDetails;
}
