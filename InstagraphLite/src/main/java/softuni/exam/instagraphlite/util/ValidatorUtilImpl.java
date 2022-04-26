package softuni.exam.instagraphlite.util;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidatorUtilImpl implements ValidatorUtil {
    private Validator validator;

    public ValidatorUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }
}
