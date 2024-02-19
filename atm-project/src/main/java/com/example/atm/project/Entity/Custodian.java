package com.example.atm.project.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Custodian")
@AllArgsConstructor(staticName = "connect")
@NoArgsConstructor
public class
Custodian {

    @Id
    @GeneratedValue
    private int id;
    private String UserName;
    private long Hundred;
    private long TwoHundred;
    private long FiveHundred;
    private String Date;


}
