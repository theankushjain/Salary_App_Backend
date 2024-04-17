package com.navodaya.SpecialLogin.dto;

import com.navodaya.SpecialLogin.entity.Location;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class LocationRequestDTO {
    private String name;
    private String state;
    private String district;
    private String type;
    private String parentLocation;
}
