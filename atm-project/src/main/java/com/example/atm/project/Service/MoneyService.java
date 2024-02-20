package com.example.atm.project.Service;

import com.example.atm.project.DTO.MoneyRequest;
import com.example.atm.project.Entity.Money;
//import com.example.atm.project.Entity.User;
import com.example.atm.project.Exception.MoneyRequestException;
import com.example.atm.project.Repository.MoneyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoneyService {
    @Autowired
    private MoneyRepo repo;
    public Money get(){
       return repo.findMoneyById(1);
    }

    public void Update(MoneyRequest req) {
        Money mon=repo.findMoneyById(1);
        if (req.getTwoHundred() != 0) {
            mon.setTwoHundred(mon.getTwoHundred()+req.getTwoHundred());
        }

        if (req.getHundred() != 0) {
            mon.setHundred(mon.getHundred()+req.getHundred());
        }
        if(req.getFiveHundred()!=0){
           mon.setFiveHundred(mon.getFiveHundred()+req.getFiveHundred());
        }
        repo.save(mon);
    }


}
