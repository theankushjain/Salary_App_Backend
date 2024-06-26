package com.navodaya.SpecialLogin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name="roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @JsonIgnore //very important to remove circular dependency in manytomany relationships. Due to this Users won't show in list.
    @ManyToMany(mappedBy="roles")
    private List<User> users;

//    @Column(name = "is_deleted")
//    private boolean deleted = false;

    // Constructor for deserialization
    public Role(String name) {
        this.name = name;
    }

    //These three Functions have been used in RoleServiceImpl
    public void removeAllUsersFromRole(){
        if (this.getUsers() != null){
            List<User> usersInRole = this.getUsers().stream().toList();
            usersInRole.forEach(this::removeUserFromRole);
        }
    }
    public void removeUserFromRole(User user) {
        user.getRoles().remove(this);
        this.getUsers().remove(user);
    }
    public void assignUserToRole(User user){
        user.getRoles().add(this);
        this.getUsers().add(user);
    }

}