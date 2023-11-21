package com.matiej.springsec_acl.user;

import com.matiej.springsec_acl.global.exceptions.EmailExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserEntity registerNewUser(UserEntity user) throws EmailExistsException {
        if (emailExist(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Cant find user: " + email));
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    private boolean emailExist(final String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
