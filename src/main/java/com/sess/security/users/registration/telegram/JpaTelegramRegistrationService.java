package com.sess.security.users.registration.telegram;

import com.sess.security.components.message.MessageId;
import com.sess.security.components.message.MessageService;
import com.sess.security.dao.repositories.SecurityUserJpaRepository;
import com.sess.security.exceptions.Error;
import com.sess.security.exceptions.ErrorBuilder;
import com.sess.security.exceptions.ErrorMessage;
import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.RegistrationException;
import com.sess.security.users.registration.exceptions.ValidateException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class JpaTelegramRegistrationService implements TelegramRegistrationService {

    private final SecurityUserJpaRepository userRepository;

    private final TelegramSecurityUserBuilder securityUserBuilder;

    private final MessageService messageService;

    public JpaTelegramRegistrationService(
            SecurityUserJpaRepository userRepository,
            TelegramSecurityUserBuilder securityUserBuilder,
            MessageService messageService) {
        this.userRepository = userRepository;
        this.securityUserBuilder = securityUserBuilder;
        this.messageService = messageService;
    }

    @Override
    @Transactional
    public SecurityUser register(TelegramUser telegramUser) throws RegistrationException {
        validate(telegramUser);
        SecurityUser newUser = securityUserBuilder.generateNewSecurityUser(telegramUser);
        return userRepository.save(newUser);
    }

    private void validate(TelegramUser telegramUser) throws ValidateException {
        Set<ErrorMessage> errors = new HashSet<>();
        if (existEmail(telegramUser.getEmail())) {
            errors.add(messageService.sayError(MessageId.UNIQUE_EMAIL_VIOLATION));
        }
        if (existTelegramId(telegramUser.getTelegramId())) {
            errors.add(messageService.sayError(MessageId.UNIQUE_TELEGRAM_ID_VIOLATION));
        }
        if (!errors.isEmpty()) {
            Error error = ErrorBuilder.listMessages(false, errors);
            throw new ValidateException(error);
        }
    }

    private boolean existEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean existTelegramId(int telegramId) {
        return userRepository.findByTelegramDataId(telegramId).isPresent();
    }

}
