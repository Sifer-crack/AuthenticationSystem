package com.personalProject.AuthSys.service;

import com.personalProject.AuthSys.model.Roles;
import com.personalProject.AuthSys.model.User;
import com.personalProject.AuthSys.repository.UserRepository;
import com.personalProject.AuthSys.request.auth.AuthenticationRequest;
import com.personalProject.AuthSys.request.auth.RegistrationRequest;
import com.personalProject.AuthSys.responce.AuthenticationResponse;
import com.personalProject.AuthSys.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistrationRequest request){
        var role = request.getRole() == 1 ? Roles.CHILD : request.getRole() == 2 ? Roles.PARENT : Roles.ADMIN;
        var user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNum(request.getPhoneNum())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        if(userRepository.findByEmail(request.getEmail()).isEmpty()){
            userRepository.save(user);
        }
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
