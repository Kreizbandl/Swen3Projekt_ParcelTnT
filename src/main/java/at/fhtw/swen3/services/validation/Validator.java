package at.fhtw.swen3.services.validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;

@Component
@Slf4j
public class Validator {

    static ValidatorFactory getValidatorFactory() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory;
    }

    javax.validation.Validator getValidator() {
        return getValidatorFactory().getValidator();
    }

    public <T> void validate(T o) {
        javax.validation.Validator validator = getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        violations.forEach(v -> log.error(v.getMessage()));
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
