package com.navodaya.SpecialLogin.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "locations")
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UniqueElements
    private String name;
    private String state;
    private String district;

    private String type; // Can be "Headquarter", "RO", or "JNV"

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Location parent;

    @JsonBackReference
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Location> children = new ArrayList<>();

    @OneToMany(mappedBy = "currentPostingLocation",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @Column(name = "is_deleted")
    private boolean deleted= false;

    // Logging fields
    @Column(nullable=false, unique=true)
    private LocalDateTime createdAt;
    @Column(nullable=false, unique=true)
    private LocalDateTime updatedAt;

    @JoinColumn(name = "createdby_id")
    @ManyToOne
    private User createdBy;

    @JoinColumn(name = "updatedby_id")
    @ManyToOne
    private User updatedBy;

    @Override
    public String toString() {
        return "Location{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", state='" + getState() + '\'' +
                ", district='" + getDistrict() + '\'' +
                ", type='" + getType() + '\'' +
                ", deleted=" + isDeleted() +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}
