package com.taenan.reduz.domain.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<ValidUrl, String> {
    private static final String URL_PATTERN = "^https?://(?:www\\.|(?!www))[\\w\\d\\-]+(?:\\.[\\w\\d]+)+[\\w\\d\\-.,@?^=%&amp;:/~+#]*$";
    
    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        return url != null && url.matches(URL_PATTERN);
    }
    
    public static boolean isValid(String url) {
        return url != null && url.matches(URL_PATTERN);
    }
}
