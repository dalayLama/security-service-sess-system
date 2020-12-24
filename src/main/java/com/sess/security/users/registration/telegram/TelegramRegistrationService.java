package com.sess.security.users.registration.telegram;

import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.RegistrationException;

public interface TelegramRegistrationService {

    SecurityUser register(TelegramUser telegramUser) throws RegistrationException;

}
