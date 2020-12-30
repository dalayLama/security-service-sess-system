package com.sess.security.users.registration;

import com.sess.security.TestUtils;
import com.sess.security.exceptions.Error;
import com.sess.security.exceptions.ErrorBuilder;
import com.sess.security.exceptions.ErrorMessage;
import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.ValidateException;
import com.sess.security.users.registration.telegram.TelegramRegistrationService;
import com.sess.security.utils.ErrorsCodes;
import com.sess.security.utils.validators.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ValidateSecurityRegistrationManagerTest {

    private static final SecurityUser SECURITY_USER = TestUtils.createSecurityUser();

    @Test
    public void shouldExecuteMethodsInRightOrderWhenRegisterTelegramUser() {
        Validator validator = mock(Validator.class);
        TelegramRegistrationService tRegService = mock(TelegramRegistrationService.class);
        when(validator.validate(any())).thenReturn(Collections.emptySet());
        when(tRegService.register(any(TelegramUser.class))).thenReturn(SECURITY_USER);

        ValidateSecurityRegistrationManager registrationManager = new ValidateSecurityRegistrationManager(validator, tRegService);
        TelegramUser telegramUser = TestUtils.createTelegramUser();
        SecurityUser registered = registrationManager.register(telegramUser);

        verify(validator).validate(telegramUser);
        verify(tRegService).register(telegramUser);
        assertThat(registered).isSameAs(SECURITY_USER);
    }

    @Test
    public void shouldThrowValidateExceptionAndNotExecuteRegisterMethodWhenRegisterTelegramUser() {
        Error error = ErrorBuilder.newBuilder(false)
                .addMessage(ErrorsCodes.VALIDATE_ERROR, "message 1")
                .addMessage(ErrorsCodes.VALIDATE_ERROR, "message 2")
                .build();
        Set<String> messages = error.getMessages().stream().map(ErrorMessage::getMessage).collect(Collectors.toSet());
        Validator validator = mock(Validator.class);
        TelegramRegistrationService tRegService = mock(TelegramRegistrationService.class);
        when(validator.validate(any())).thenReturn(messages);

        ValidateSecurityRegistrationManager registrationManager = new ValidateSecurityRegistrationManager(validator, tRegService);
        TelegramUser telegramUser = TestUtils.createTelegramUser();

        ValidateException thrown = assertThrows(
                ValidateException.class,
                () -> registrationManager.register(telegramUser)
        );

        verify(validator).validate(telegramUser);
        verify(tRegService, never()).register(telegramUser);

        ArrayList<ErrorMessage> errorMessages = new ArrayList<>(thrown.getError().getMessages());
        assertThat(errorMessages).containsExactlyInAnyOrderElementsOf(error.getMessages());
    }

}