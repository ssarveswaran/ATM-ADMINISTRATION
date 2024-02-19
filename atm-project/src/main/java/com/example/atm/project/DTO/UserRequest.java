package com.example.atm.project.DTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@AllArgsConstructor(staticName = "connect")
@NoArgsConstructor
@Data
public class UserRequest {
    @Pattern(regexp = "^[a-zA-Z]+$",message="Invalid name")
    @NotNull(message = "UserName should not be null")
    private String name;
    @NotNull(message = "password should not be null")
    private String password;
    @NotNull(message = "Not be Null")
    @Enumerated(EnumType.STRING)
    private Role role;




    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
