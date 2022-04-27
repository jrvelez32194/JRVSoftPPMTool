package jrvsoft.ppmtool.validator;

import jrvsoft.ppmtool.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getPassword() == null) {
            errors.rejectValue("password", "Null", "password must required");
        } else if (user.getConfirmPassword() == null) {
            errors.rejectValue("confirmPassword", "Null", "Confirm password must required");
        } else {
            if (user.getPassword().length() < 6) {
                errors.rejectValue("password", "length", "password must be at least 6 characters");
            }
            if (!ObjectUtils.nullSafeEquals(user.getPassword(), user.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "Match", "password must match");
            }
        }
    }
}
