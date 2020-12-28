package com.sess.security.users.registration.telegram;

import com.sess.security.dao.repositories.SecurityUserJpaRepository;
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

    private String messageViolationUniqueEmail = "Пользователь с таким \"email\" уже существует";

    private String messageViolationUniqueTelegramId = "Пользователь с указанным \"telegram id\" уже существует";

    public JpaTelegramRegistrationService(
            SecurityUserJpaRepository userRepository,
            TelegramSecurityUserBuilder securityUserBuilder) {
        this.userRepository = userRepository;
        this.securityUserBuilder = securityUserBuilder;
    }

    public String getMessageViolationUniqueEmail() {
        return messageViolationUniqueEmail;
    }

    public void setMessageViolationUniqueEmail(String messageViolationUniqueEmail) {
        this.messageViolationUniqueEmail = messageViolationUniqueEmail;
    }

    public String getMessageViolationUniqueTelegramId() {
        return messageViolationUniqueTelegramId;
    }

    public void setMessageViolationUniqueTelegramId(String messageViolationUniqueTelegramId) {
        this.messageViolationUniqueTelegramId = messageViolationUniqueTelegramId;
    }

    @Override
    @Transactional
    public SecurityUser register(TelegramUser telegramUser) throws RegistrationException {
        validate(telegramUser);
        SecurityUser newUser = securityUserBuilder.generateNewSecurityUser(telegramUser);
        return userRepository.save(newUser);
    }

    private void validate(TelegramUser telegramUser) throws ValidateException {
        Set<String> errors = new HashSet<>();
        if (existEmail(telegramUser.getEmail())) {
            errors.add(messageViolationUniqueEmail);
        }
        if (existTelegramId(telegramUser.getTelegramId())) {
            errors.add(messageViolationUniqueTelegramId);
        }
        if (!errors.isEmpty()) {
            throw new ValidateException(errors);
        }
    }

    private boolean existEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean existTelegramId(int telegramId) {
        return userRepository.findByTelegramDataId(telegramId).isPresent();
    }

}
