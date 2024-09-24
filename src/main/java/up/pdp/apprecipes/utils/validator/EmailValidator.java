package up.pdp.apprecipes.utils.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import up.pdp.apprecipes.repository.UserRepository;
import up.pdp.apprecipes.utils.annotations.Email;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<Email, String> {

    private final UserRepository userRepository;

    public EmailValidator() {
        this.userRepository = null;
    }

    private boolean required;
    private boolean unique;

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public void initialize(Email constraintAnnotation) {
        this.required = constraintAnnotation.required();
        this.unique = constraintAnnotation.unique();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required && value == null) {
            return true;
        }

        if (value == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email is required")
                    .addConstraintViolation();
            return false;
        }

        if (!pattern.matcher(value).matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid email format")
                    .addConstraintViolation();
            return false;
        }

        if (unique && userRepository.existsByEmailAndDeletedFalse(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email already exists")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
