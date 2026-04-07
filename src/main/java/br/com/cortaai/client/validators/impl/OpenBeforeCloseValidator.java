package br.com.cortaai.client.validators.impl;

import br.com.cortaai.client.interfaces.HasOpenCloseTime;
import br.com.cortaai.client.validators.annotations.OpenBeforeClose;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OpenBeforeCloseValidator implements ConstraintValidator<OpenBeforeClose, HasOpenCloseTime> {

    @Override
    public boolean isValid(HasOpenCloseTime value, ConstraintValidatorContext context) {
        if (value.hrOpensAt() == null || value.hrClosesAt() == null) {
            return true;
        }

        return value.hrOpensAt().isBefore(value.hrClosesAt());
    }
}
