package com.matiej.springsec_acl.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

@Getter
@Setter
@Table(name = "users")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String UUID = randomUUID().toString();
    private String name;
    @JsonIgnore // in order to not display the password anywhere
    private String password;
    @JsonIgnore
    private String matchingPassword;
    private String roles;
    private String email;

    public Set<String> getRoles() {
        return Arrays.stream(roles.split(",")).collect(Collectors.toSet());
    }

    public void setRoles(Set<String> roles) {
        this.roles = String.join(",", roles);
    }
}
