package com.sess.security.users.registration;

import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.RegistrationException;
import com.sess.security.users.registration.telegram.TelegramRegistrationService;
import org.springframework.stereotype.Service;

@Service
public class SimpleRegistrationManager implements RegistrationManager {

    private final TelegramRegistrationService telegramRegistrationService;

    public SimpleRegistrationManager(TelegramRegistrationService telegramRegistrationService) {
        this.telegramRegistrationService = telegramRegistrationService;
    }

    @Override
    public SecurityUser register(TelegramUser telegramUser) throws RegistrationException {
        return this.telegramRegistrationService.register(telegramUser);
    }
}
