package com.personalProject.AuthSys.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String phoneNum;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public User(){}

    public User(String name, String lastname, String email, String phoneNum, String password) {
        this.name = name;
        this.lastName = lastname;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    public User(Long id, String name, String lastname, String email, String phoneNum, String password, Roles role) {
        this.id = id;
        this.name = name;
        this.lastName = lastname;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
