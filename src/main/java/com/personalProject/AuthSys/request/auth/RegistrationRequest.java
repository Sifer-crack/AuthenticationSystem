package com.personalProject.AuthSys.request.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String name;
    private String lastName;
    private String email;
    private String phoneNum;
    private String password;
    private Integer role;
}
