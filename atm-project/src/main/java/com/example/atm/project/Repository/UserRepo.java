package com.example.atm.project.Repository;

import com.example.atm.project.Entity.MyUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<MyUser,Integer>{

    MyUser findUserById(int id);


    Optional<MyUser> findByName(String username);
}

