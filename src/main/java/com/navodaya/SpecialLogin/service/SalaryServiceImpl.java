package com.navodaya.SpecialLogin.service;

import com.navodaya.SpecialLogin.dto.SalaryRequestDTO;
import com.navodaya.SpecialLogin.dto.SalaryResponseDTO;
import com.navodaya.SpecialLogin.entity.Menu;
import com.navodaya.SpecialLogin.entity.SalaryDetails.OtherAllowances;
import com.navodaya.SpecialLogin.entity.SalaryDetails.OtherDeductions;
import com.navodaya.SpecialLogin.entity.SalaryDetails.SalaryDetails;
import com.navodaya.SpecialLogin.entity.User;
import com.navodaya.SpecialLogin.repository.MenuRepository;
import com.navodaya.SpecialLogin.repository.SalaryRepository.OtherAllowancesRepository;
import com.navodaya.SpecialLogin.repository.SalaryRepository.OtherDeductionsRepository;
import com.navodaya.SpecialLogin.repository.SalaryRepository.SalaryRepository;
import com.navodaya.SpecialLogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private OtherAllowancesRepository otherAllowancesRepository;
    @Autowired
    private OtherDeductionsRepository otherDeductionsRepository;
    @Autowired
    private UserRepository userRepository;

    public List<SalaryDetails> getUsersSalary(Long userId){
        System.out.println(userId);
        return userRepository.findSalaryDetailsByUserId(userId);
//        return salaryRepository.findAllNotDeleted();
    }

//    public List<SalaryDetails> getUsersLatestSalary(Long userId){
//        return userRepository.findLatestSalaryDetailsByUserId(userId);
////        return salaryRepository.findAllNotDeleted();
//    }


    @Override
    public SalaryDetails addSalary(SalaryRequestDTO salaryRequest, User currentUser){
        Optional<User> userOptional = userRepository.findById(salaryRequest.getUserId());
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        SalaryDetails salaryDetails = SalaryDetails.build(null,userOptional.get(),salaryRequest.getPayLevel(),salaryRequest.getYear(),salaryRequest.getMonth(),salaryRequest.getDaysPresent(),salaryRequest.getBasicPay(),new ArrayList<>(),0,new ArrayList<>(),0,0,0,false, LocalDateTime.now(),LocalDateTime.now(),currentUser,currentUser);
        SalaryDetails savedSalaryDetails = salaryRepository.save(salaryDetails);

        List<OtherAllowances> allowances = salaryRequest.getAllowances().stream()
                .map(allowance -> {
                    allowance.setSalaryDetails(savedSalaryDetails);
                    return allowance;
                })
                .collect(Collectors.toList());
        otherAllowancesRepository.saveAll(allowances);

        // Associate the OtherDeductions with the saved SalaryDetails
        List<OtherDeductions> deductions = salaryRequest.getDeductions().stream()
                .map(deduction -> {
                    deduction.setSalaryDetails(savedSalaryDetails);
                    return deduction;
                })
                .collect(Collectors.toList());
        otherDeductionsRepository.saveAll(deductions);

        int totalAllowances = allowances.stream()
                .mapToInt(OtherAllowances::getAmountOfAllowance)
                .sum();
        savedSalaryDetails.setTotalAmountAllowances(totalAllowances);

        // Calculate and set the total amount of deductions
        int totalDeductions = deductions.stream()
                .mapToInt(OtherDeductions::getAmountOfDeduction)
                .sum();
        savedSalaryDetails.setTotalAmountDeductions(totalDeductions);

        // Calculate and set the gross salary
        int grossSalary = salaryDetails.getBasicPay() + totalAllowances;
        savedSalaryDetails.setGrossSalary(grossSalary);

        // Calculate and set the net salary
        int netSalary = grossSalary - totalDeductions;
        savedSalaryDetails.setNetSalary(netSalary);

        return salaryRepository.save(savedSalaryDetails);

    }

    }

