package com.example.atm.project.DTO;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "connect")
@NoArgsConstructor

public class MoneyRequest {

//    @Pattern(regexp = "[0-9]*",message="Give the Correct Input")
    private long Hundred;
//    @Pattern(regexp = "[0-9]*",message="Give the Correct Input")
    private long TwoHundred;
//    @Pattern(regexp = "[0-9]*",message="Give the Correct Input")
    private long FiveHundred;

}
