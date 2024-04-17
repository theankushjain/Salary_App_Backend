package com.navodaya.SpecialLogin.service;

import com.navodaya.SpecialLogin.dto.LocationRequestDTO;
import com.navodaya.SpecialLogin.dto.LocationResponseDTO;
import com.navodaya.SpecialLogin.dto.MenuRequestDTO;
import com.navodaya.SpecialLogin.entity.Location;
import com.navodaya.SpecialLogin.entity.Menu;
import com.navodaya.SpecialLogin.entity.User;
import com.navodaya.SpecialLogin.exception.LocationNotFoundException;
import com.navodaya.SpecialLogin.exception.MenuNotFoundException;

import java.util.List;

public interface LocationService {
    List<LocationResponseDTO> getAllLocations(); //Read

    Location createLocation(LocationRequestDTO theLocation, User currentUser); //Create

    Location softDeleteLocation(Long locationId, User currentUser) throws LocationNotFoundException; //Delete

    Location updateLocation(LocationRequestDTO locationRequest, Long locationId, User currentUser) throws LocationNotFoundException;

}
