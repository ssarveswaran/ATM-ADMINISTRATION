package com.example.atm.project.Service;


import com.example.atm.project.DTO.CustodianRequest;
import com.example.atm.project.DTO.MoneyRequest;
import com.example.atm.project.Entity.Custodian;
import com.example.atm.project.Repository.CustodianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CustodianService {


    @Autowired
    private CustodianRepo rep;
    @Autowired
    private MoneyService ser;
    File file=new File("D:\\backupatm.txt");
    public Custodian AddMoney(CustodianRequest req) {

        Custodian aud=Custodian.connect(0,req.getUserName(),req.getHundred(),req.getTwoHundred()
        ,req.getFiveHundred(),req.getDate());
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(req.toString());
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
     ser.Update(MoneyRequest.connect(req.getHundred(),req.getTwoHundred(),req.getFiveHundred()));
        return rep.save(aud);
    }
    public List<Custodian> GetReport(){
        return rep.findAll();
    }

}
