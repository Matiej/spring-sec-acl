package com.matiej.springsec_acl.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@ConfigurationProperties("app.security.admin")
public class DefaultAdmin {
    private String username;
    private String email;
    private String password;
    private Set<String> roles;

    User adminToUser() {
        return new User(
                this.username,
                this.password,
                roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet()));
    }
}
