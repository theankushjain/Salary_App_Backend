package com.navodaya.SpecialLogin.service;

import com.navodaya.SpecialLogin.dto.LocationRequestDTO;
import com.navodaya.SpecialLogin.dto.LocationResponseDTO;
import com.navodaya.SpecialLogin.dto.MenuRequestDTO;
import com.navodaya.SpecialLogin.entity.Location;
import com.navodaya.SpecialLogin.entity.Menu;
import com.navodaya.SpecialLogin.entity.User;
import com.navodaya.SpecialLogin.exception.LocationNotFoundException;
import com.navodaya.SpecialLogin.exception.MenuNotFoundException;
import com.navodaya.SpecialLogin.repository.LocationRepository;
import com.navodaya.SpecialLogin.repository.MenuRepository;
import com.navodaya.SpecialLogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService{
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;
    public List<LocationResponseDTO> getAllLocations(){
        List<Location> locations = locationRepository.findAllNotDeleted();
        List<LocationResponseDTO> responseLocations = locations.stream()
                .map(this::mapToLocationResponseDTO)
                .collect(Collectors.toList());
        return responseLocations;
    }

    private LocationResponseDTO mapToLocationResponseDTO(Location location) {
        LocationResponseDTO dto = new LocationResponseDTO();
        dto.setId(location.getId());
        dto.setName(location.getName());
        dto.setState(location.getState());
        dto.setDistrict(location.getDistrict());
        dto.setType(location.getType());
        if (location.getParent()!=null){
            Optional<Location> parent = locationRepository.findById(location.getParent().getId());
            if (parent.isPresent()) {
                dto.setParent(mapToLocationResponseDTO(parent.get()));
            }
        }
        return dto;
    }


    public Location softDeleteLocation(Long locationId, User currentUser) throws LocationNotFoundException {
        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isPresent()){
            Location deletedLocation=new Location();
            deletedLocation = Location.build(location.get().getId(),location.get().getName(),location.get().getState(),location.get().getDistrict(),location.get().getType(),location.get().getParent(),location.get().getChildren(),location.get().getUsers(),true,location.get().getCreatedAt(), LocalDateTime.now(),location.get().getCreatedBy(),currentUser);
            return locationRepository.save(deletedLocation);
        }else throw new LocationNotFoundException("Location not found with location Id: "+ locationId);
    }

    public Location updateLocation(LocationRequestDTO locationRequest, Long locationId, User currentUser) throws LocationNotFoundException{
        Optional<Location> existingLocationOptional = locationRepository.findById(locationId);
        System.out.println(locationRequest);
        Location parentLocation = locationRepository.findByName(locationRequest.getParentLocation());
        Location foundLocation = new Location();
        if (parentLocation!=null){
            foundLocation = locationRepository.findByName(parentLocation.getName());
        }else {
            foundLocation=null;
        }
        if (existingLocationOptional.isPresent()) {
            Location existingLocation = existingLocationOptional.get();
            existingLocation = Location.build(existingLocation.getId(),locationRequest.getName(),locationRequest.getState(),locationRequest.getDistrict(),locationRequest.getType(),foundLocation,existingLocation.getChildren(),existingLocation.getUsers(),false, existingLocation.getCreatedAt(),LocalDateTime.now(),existingLocation.getCreatedBy(),currentUser);
            return locationRepository.save(existingLocation);
        }
        else {
            throw new LocationNotFoundException("Menu not found with id:" + locationId);
        }

    }

    public Location createLocation(LocationRequestDTO locationRequest, User currentUser){
        Location parentLocation = locationRepository.findByName(locationRequest.getParentLocation());

        // Check if the parent location is not null
        if (parentLocation != null) {
            // Check if the parent location is already persisted in the database
            Optional<Location> foundParent = locationRepository.findById(parentLocation.getId());

            // If the parent location is not found, save it first
            if (foundParent.isEmpty()) {
                parentLocation = locationRepository.save(parentLocation);
            } else {
                // Use the existing parent location
                parentLocation = foundParent.get();
            }
        }

        // Create the new location with the parent
        Location location = Location.build(
                null, locationRequest.getName(), locationRequest.getState(), locationRequest.getDistrict(),
                locationRequest.getType(), parentLocation, null, null, false, LocalDateTime.now(),
                LocalDateTime.now(), currentUser, currentUser
        );

        return locationRepository.save(location);
    }

}
