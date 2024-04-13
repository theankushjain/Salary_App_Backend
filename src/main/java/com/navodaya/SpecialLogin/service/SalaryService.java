package com.navodaya.SpecialLogin.service;


import com.navodaya.SpecialLogin.dto.SalaryRequestDTO;
import com.navodaya.SpecialLogin.dto.SalaryResponseDTO;
import com.navodaya.SpecialLogin.entity.SalaryDetails.SalaryDetails;
import com.navodaya.SpecialLogin.entity.User;


import java.util.List;

public interface SalaryService {

//    List<SalaryDetails> getAllSalary();
//    List<SalaryDetails> getCurrentUsersSalary(User user);
//
//    SalaryDetails getOneSalary(long userID, long SalaryId);

    SalaryDetails addSalary(SalaryRequestDTO salaryRequest, User currentUser);

    List<SalaryDetails> getUsersSalary(Long userId);

//    List<SalaryDetails> getUsersLatestSalary(Long userId);

//    SalaryDetails softDeleteSalary(Long salaryId, User currentUser) throws SalaryNotFoundException; //Delete
//
//    Menu updateSalary(SalaryRequestDTO salaryRequest, Long salaryId, User currentUser) throws SalaryNotFoundException;
}
