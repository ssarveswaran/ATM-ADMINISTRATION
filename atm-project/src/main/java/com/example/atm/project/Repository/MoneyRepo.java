package com.example.atm.project.Repository;

import com.example.atm.project.Entity.Money;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyRepo extends JpaRepository<Money,Integer> {
    Money findMoneyById(int i);
}
