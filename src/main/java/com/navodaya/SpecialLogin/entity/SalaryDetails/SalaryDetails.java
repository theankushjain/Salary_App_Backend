package com.navodaya.SpecialLogin.entity.SalaryDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.navodaya.SpecialLogin.entity.User;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name="salary_details")
//@JsonIgnoreProperties(value = {"user"}, allowSetters = true)
public class SalaryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private long payLevel;

    private int year;

    private int month;

    private int noOfDaysPresent;

    private int basicPay;

    @OneToMany(mappedBy = "salaryDetails",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<OtherAllowances> otherAllowances = new ArrayList<>();

    private int totalAmountAllowances;

    @OneToMany(mappedBy = "salaryDetails", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<OtherDeductions> otherDeductions = new ArrayList<>();

    int totalAmountDeductions;
    int grossSalary;
    int netSalary;

    @Column(name = "is_deleted")
    private boolean deleted= false;

    // Logging fields
    @Column(nullable=false)
    private LocalDateTime createdAt;
    @Column(nullable=false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @JoinColumn(name = "createdby_id")
    @ManyToOne
    private User createdBy;

    @JsonIgnore
    @JoinColumn(name = "updatedby_id")
    @ManyToOne
    private User updatedBy;
}
