package com.navodaya.SpecialLogin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.navodaya.SpecialLogin.entity.Location;
import com.navodaya.SpecialLogin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponseDTO {
    private Long id;
    private String name;
    private String state;
    private String district;
    private String type;
    private LocationResponseDTO parent;
}
