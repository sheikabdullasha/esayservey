package com.formbuilder.easyservey.DataValidation;

import com.formbuilder.easyservey.entity.User;
import com.formbuilder.easyservey.repo.IUserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final IUserRepository repo;
    public void initialize(UniqueEmail constraint) {
    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {

        Optional<User> inDb=repo.findByEmail1(obj);
        if (inDb.isPresent()){
            return false;
        }
        return true;
    }
}
