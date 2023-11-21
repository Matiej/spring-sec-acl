package com.matiej.springsec_acl.global.validators;

import com.matiej.springsec_acl.user.UserEntity;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PassMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        final UserEntity user = (UserEntity) o;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}