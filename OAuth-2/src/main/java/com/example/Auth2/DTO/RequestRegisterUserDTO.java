package com.example.Auth2.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestRegisterUserDTO {

    private String username;
    private String password;
    private String email;
}
