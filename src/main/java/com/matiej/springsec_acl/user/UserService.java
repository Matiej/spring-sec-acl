package com.matiej.springsec_acl.user;

import com.matiej.springsec_acl.global.exceptions.EmailExistsException;

import java.util.List;

public interface UserService {

    UserEntity registerNewUser(UserEntity user) throws EmailExistsException;

    UserEntity findUserByEmail(String email);

    List<UserEntity> findAll();
}
