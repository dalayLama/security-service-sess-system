package com.sess.security.users.registration.telegram;

import com.sess.security.dao.repositories.SecurityUserJpaRepository;
import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.RegistrationException;
import com.sess.security.users.registration.exceptions.ValidateException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class JpaTelegramRegistrationService implements TelegramRegistrationService {

    private final SecurityUserJpaRepository userRepository;

    private final TelegramSecurityUserBuilder securityUserBuilder;

    public JpaTelegramRegistrationService(
            SecurityUserJpaRepository userRepository,
            TelegramSecurityUserBuilder securityUserBuilder) {
        this.userRepository = userRepository;
        this.securityUserBuilder = securityUserBuilder;
    }

    @Override
    @Transactional
    public SecurityUser register(TelegramUser telegramUser) throws RegistrationException {
        validate(telegramUser);
        SecurityUser newUser = securityUserBuilder.generateNewSecurityUser(telegramUser);
        return userRepository.save(newUser);
    }

    private void validate(TelegramUser telegramUser) throws ValidateException {
        // todo валидация
    }

}
