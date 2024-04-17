package com.navodaya.SpecialLogin.repository;

import com.navodaya.SpecialLogin.entity.Location;
import com.navodaya.SpecialLogin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByName(String name);
    List<Location> findByParent(Location parent);

    @Query("SELECT l FROM Location l WHERE l.deleted = false")
    List<Location> findAllNotDeleted();
}
