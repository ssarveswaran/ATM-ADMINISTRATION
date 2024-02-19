package com.example.atm.project.Repository;


import com.example.atm.project.Entity.Custodian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustodianRepo extends JpaRepository<Custodian,Integer>{

}