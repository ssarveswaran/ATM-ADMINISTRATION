package com.example.atm.project.DTO;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor(staticName = "connect")
@NoArgsConstructor
public class CustodianRequest{
    @NotNull(message="UserName Not Be Null")
    @Pattern(regexp = "^[a-zA-Z]+$",message="Invalid name")
    private String UserName;
    @Min(value = 1, message = "Value must be at least 1")
    @Max(value = 1000, message = "Value must be at most 1000")
    private long Hundred;
    @Min(value = 1, message = "Value must be at least 1")
    @Max(value = 1000, message = "Value must be at most 1000")
    private long TwoHundred;
    @Min(value = 1, message = "Value must be at least 1")
    @Max(value = 1000, message = "Value must be at most 1000")
    private long FiveHundred;
    private String Date;

    @Override
    public String toString() {
        return "Custodian{" +
                "Date='" + Date + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Hundred=" + Hundred +
                ", TwoHundred=" + TwoHundred +
                ", FiveHundred=" + FiveHundred +
                '}';
    }

}
