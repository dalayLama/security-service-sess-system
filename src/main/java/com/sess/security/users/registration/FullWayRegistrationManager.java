package com.sess.security.users.registration;

import com.sess.security.core.services.CoreService;
import com.sess.security.core.services.exceptions.CoreServiceException;
import com.sess.security.models.user.*;
import com.sess.security.users.registration.exceptions.RegistrationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class FullWayRegistrationManager implements RegistrationManager {

    private final SecurityRegistrationManager securityRegistrationManager;

    private final CoreService coreService;

    public FullWayRegistrationManager(SecurityRegistrationManager securityRegistrationManager, CoreService coreService) {
        this.securityRegistrationManager = securityRegistrationManager;
        this.coreService = coreService;
    }

    @Override
    @Transactional
    public RegisteredCoreUser telegramRegister(TelegramUser telegramUser) throws RegistrationException {
        SecurityUser registered = securityRegistrationManager.register(telegramUser);
        return registerInCore(telegramUser, registered.getSecurityKey());
    }

    private RegisteredCoreUser registerInCore(BaseUser user, UUID securityKey) {
        CoreUser coreUser = UsersUtils.createCoreUser(user, securityKey);
        try {
            return coreService.register(coreUser);
        } catch (CoreServiceException e) {
            throw new RegistrationException(e, e.getResponse());
        }
    }

}
