package com.example.atm.project.Controller;


import com.example.atm.project.DTO.CustodianRequest;
import com.example.atm.project.Entity.Custodian;
import com.example.atm.project.Entity.Money;
import com.example.atm.project.Service.CustodianService;
import com.example.atm.project.Service.MoneyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("Atm/v1.0/Custodian")
@SecurityRequirement(name = "basicAuth")
public class CustodianController {
    @Autowired
     private CustodianService ser;

    @Autowired
    private MoneyService Ser;
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('CUSTODIAN')")
    public ResponseEntity<List<Money>> get(){
        return new ResponseEntity<>(Ser.get(), HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('CUSTODIAN')")
    public ResponseEntity<Custodian> Add(@RequestBody @Valid CustodianRequest req){
        Date d1 = new Date();
       return new ResponseEntity<>(ser.AddMoney(req.connect(req.getUserName(),req.getHundred()
        ,req.getTwoHundred(),req.getFiveHundred(), String.valueOf(d1))), HttpStatus.CREATED);
    }
//    @GetMapping("/GetAuditor")
//    public ResponseEntity<List<Auditor>> get(){
//        return new ResponseEntity<>(ser.GetAuditor(),HttpStatus.OK);
//    }
//    @GetMapping("/GetAuditor/{id}")
//    public ResponseEntity<Auditor> getByName(@PathVariable("id") String id){
//        return new ResponseEntity<>(ser.getId(Integer.parseInt(id)),HttpStatus.OK);
//    }

}
