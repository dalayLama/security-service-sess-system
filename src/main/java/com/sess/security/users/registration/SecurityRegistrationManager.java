package com.sess.security.users.registration;

import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.RegistrationException;

/**
 * The registration manager which registers users only in security services
 */
public interface SecurityRegistrationManager {

    SecurityUser register(TelegramUser telegramUser) throws RegistrationException;

}
