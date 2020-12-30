package com.sess.security.core.services;

import com.sess.security.core.services.exceptions.CoreServiceException;
import com.sess.security.models.user.CoreUser;
import com.sess.security.models.user.RegisteredCoreUser;

/**
 * Interface for communicating CORE-SERVICE
 */
public interface CoreService {

    RegisteredCoreUser register(CoreUser coreUser) throws CoreServiceException;

}
