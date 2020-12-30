package com.sess.security.users.registration;

import com.sess.security.models.user.RegisteredCoreUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.RegistrationException;

/**
 * The main registration manager which registers users in security-service and core-service
 */
public interface RegistrationManager {

    RegisteredCoreUser telegramRegister(TelegramUser telegramUser) throws RegistrationException;

}
