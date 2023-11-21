package com.matiej.springsec_acl.security;

import com.matiej.springsec_acl.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEntityDetailService implements UserDetailsService {
    private final UserService userService;
    private final DefaultAdmin defaultAdmin;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(defaultAdmin.getEmail().equals(username)) {
            return defaultAdmin.adminToUser();
        }
        return new UserEntityDetails(userService.findUserByEmail(username));
    }
}
