package com.sess.security.users.registration.core;

import com.sess.security.models.user.CoreUser;
import com.sess.security.users.registration.exceptions.RegistrationException;

/**
 * The service for registration in the core service
 */
public interface CoreRegistrationService {

    CoreUser register(CoreUser coreUser) throws RegistrationException;

}
