package com.navodaya.SpecialLogin.dto;

import com.navodaya.SpecialLogin.entity.Location;
import com.navodaya.SpecialLogin.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Location currentPostingLocation;
    private List<Role> roles;
}