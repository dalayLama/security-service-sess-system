package com.sess.security.users.registration;

import com.sess.security.models.user.BaseUser;
import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.RegistrationException;
import com.sess.security.users.registration.exceptions.ValidateException;
import com.sess.security.users.registration.telegram.TelegramRegistrationService;
import com.sess.security.utils.validators.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidateSecurityRegistrationManager implements SecurityRegistrationManager {

    private final Validator validator;

    private final TelegramRegistrationService telegramRegistrationService;

    public ValidateSecurityRegistrationManager(Validator validator, TelegramRegistrationService telegramRegistrationService) {
        this.validator = validator;
        this.telegramRegistrationService = telegramRegistrationService;
    }

    @Override
    public SecurityUser register(TelegramUser telegramUser) throws RegistrationException {
        validate(telegramUser);
        return this.telegramRegistrationService.register(telegramUser);
    }

    private void validate(BaseUser user) throws ValidateException {
        Set<String> result = validator.validate(user);
        if (!result.isEmpty()) {
            throw new ValidateException(result);
        }
    }

}
