package com.navodaya.SpecialLogin.controller;

import com.navodaya.SpecialLogin.dto.LocationRequestDTO;
import com.navodaya.SpecialLogin.dto.LocationResponseDTO;
import com.navodaya.SpecialLogin.dto.MenuRequestDTO;
import com.navodaya.SpecialLogin.entity.Location;
import com.navodaya.SpecialLogin.entity.Menu;
import com.navodaya.SpecialLogin.entity.User;
import com.navodaya.SpecialLogin.exception.LocationNotFoundException;
import com.navodaya.SpecialLogin.exception.MenuNotFoundException;
import com.navodaya.SpecialLogin.exception.TokenNotFoundException;
import com.navodaya.SpecialLogin.service.LocationService;
import com.navodaya.SpecialLogin.service.MenuService;
import com.navodaya.SpecialLogin.utils.ExtractUserFromRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private ExtractUserFromRequest extractUserFromRequest;

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/")
    public ResponseEntity<List<LocationResponseDTO>> getLocations(){
        ResponseEntity<List<LocationResponseDTO>> locations = ResponseEntity.ok(locationService.getAllLocations());
        return locations;
    }

    @CrossOrigin (origins = "http://localhost:4200/")
    @PostMapping("/add")
    public ResponseEntity<Location> addNewLocation(HttpServletRequest request, @RequestBody @Valid LocationRequestDTO addLocationInfo) throws TokenNotFoundException {
        User user =  extractUserFromRequest.extractCurrentUser(request);
        return new ResponseEntity<>(locationService.createLocation(addLocationInfo, user), CREATED);
    }

    @CrossOrigin
    @PutMapping("/{locationId}")
    public ResponseEntity<Location> editLocation(HttpServletRequest request, @RequestBody @Valid LocationRequestDTO editLocationInfo, @PathVariable Long locationId) throws MenuNotFoundException, TokenNotFoundException, LocationNotFoundException {
        User user = extractUserFromRequest.extractCurrentUser(request);
        return new ResponseEntity<>(locationService.updateLocation(editLocationInfo, locationId, user), OK);
    }

    @CrossOrigin
    @DeleteMapping("/{locationId}")
    public ResponseEntity<Location> deleteLocation(HttpServletRequest request, @PathVariable Long locationId) throws TokenNotFoundException, MenuNotFoundException, LocationNotFoundException {
        User user=extractUserFromRequest.extractCurrentUser(request);
        return new ResponseEntity<>(locationService.softDeleteLocation(locationId, user), OK);
    }
}
