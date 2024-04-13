package com.navodaya.SpecialLogin.controller;

import com.navodaya.SpecialLogin.dto.SalaryRequestDTO;
import com.navodaya.SpecialLogin.dto.SalaryResponseDTO;
import com.navodaya.SpecialLogin.entity.SalaryDetails.SalaryDetails;
import com.navodaya.SpecialLogin.entity.User;
import com.navodaya.SpecialLogin.exception.TokenNotFoundException;
import com.navodaya.SpecialLogin.service.SalaryService;
import com.navodaya.SpecialLogin.utils.ExtractUserFromRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private ExtractUserFromRequest extractUserFromRequest;

    @Autowired
    private SalaryService salaryService;
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/{userId}")
    public ResponseEntity<List<SalaryDetails>> getUsersSalary(@PathVariable Long userId) {
        return ResponseEntity.ok(salaryService.getUsersSalary(userId));
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/currentuser")
    public ResponseEntity<List<SalaryDetails>> getUsersSalary(HttpServletRequest request) throws TokenNotFoundException {
        User currentUser =  extractUserFromRequest.extractCurrentUser(request);
        return ResponseEntity.ok(salaryService.getUsersSalary(currentUser.getId()));
    }


    @CrossOrigin (origins = "http://localhost:4200/")
    @PostMapping("/add")
    public ResponseEntity<SalaryDetails> addNewSalary(HttpServletRequest request, @RequestBody @Valid SalaryRequestDTO addSalaryInfo) throws TokenNotFoundException {
        User currentUser =  extractUserFromRequest.extractCurrentUser(request);
        return new ResponseEntity<>(salaryService.addSalary(addSalaryInfo, currentUser), CREATED);
    }
}
