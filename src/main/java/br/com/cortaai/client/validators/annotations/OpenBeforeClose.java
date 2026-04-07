package br.com.cortaai.client.validators.annotations;

import br.com.cortaai.client.validators.impl.OpenBeforeCloseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OpenBeforeCloseValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenBeforeClose {
    String message() default "Opening time must be before closing time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
