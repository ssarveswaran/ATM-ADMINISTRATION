package com.example.atm.project.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Entity
@Data
@Table(name="MyUser")
@NoArgsConstructor
@AllArgsConstructor(staticName = "connect")
public class MyUser {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String name;
    private String password;
    private String role;



}
