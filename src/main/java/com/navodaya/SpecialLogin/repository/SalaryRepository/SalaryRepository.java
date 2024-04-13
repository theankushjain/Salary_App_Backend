package com.navodaya.SpecialLogin.repository.SalaryRepository;

import com.navodaya.SpecialLogin.entity.Menu;
import com.navodaya.SpecialLogin.entity.Role;
import com.navodaya.SpecialLogin.entity.SalaryDetails.SalaryDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalaryRepository extends JpaRepository<SalaryDetails,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE SalaryDetails s SET s.deleted = true WHERE s.id = :salaryDetailsId")
    int softDelete(@Param("salaryDetailsId") Long salaryDetailsId);

    @Query("SELECT s FROM SalaryDetails s WHERE s.deleted = false")
    List<SalaryDetails> findAllNotDeleted();
}
