package com.sess.security.users.registration;

import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.RegistrationException;

/**
 * General registration manager who delegates an operation to a specific service
 */
public interface RegistrationManager {

    SecurityUser register(TelegramUser telegramUser) throws RegistrationException;

}
