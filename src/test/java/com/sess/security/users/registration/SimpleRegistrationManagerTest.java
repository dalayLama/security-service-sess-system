package com.sess.security.users.registration;

import com.sess.security.TestUtils;
import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.ValidateException;
import com.sess.security.users.registration.telegram.TelegramRegistrationService;
import com.sess.security.utils.validators.Validator;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SimpleRegistrationManagerTest {

    private static final SecurityUser SECURITY_USER = TestUtils.createSecurityUser();

    @Test
    public void shouldExecuteMethodsInRightOrderWhenRegisterTelegramUser() {
        Validator validator = mock(Validator.class);
        TelegramRegistrationService tRegService = mock(TelegramRegistrationService.class);
        when(validator.validate(any())).thenReturn(Collections.emptySet());
        when(tRegService.register(any(TelegramUser.class))).thenReturn(SECURITY_USER);

        SimpleRegistrationManager registrationManager = new SimpleRegistrationManager(validator, tRegService);
        TelegramUser telegramUser = TestUtils.createTelegramUser();
        SecurityUser registered = registrationManager.register(telegramUser);

        verify(validator).validate(telegramUser);
        verify(tRegService).register(telegramUser);
        assertThat(registered).isSameAs(SECURITY_USER);
    }

    @Test
    public void shouldThrowValidateExceptionAndNotExecuteRegisterMethodWhenRegisterTelegramUser() {
        Set<String> validateMessages = Set.of(
                "message 1",
                "message 2"
        );
        Validator validator = mock(Validator.class);
        TelegramRegistrationService tRegService = mock(TelegramRegistrationService.class);
        when(validator.validate(any())).thenReturn(validateMessages);

        SimpleRegistrationManager registrationManager = new SimpleRegistrationManager(validator, tRegService);
        TelegramUser telegramUser = TestUtils.createTelegramUser();

        ValidateException thrown = assertThrows(
                ValidateException.class,
                () -> registrationManager.register(telegramUser)
        );

        verify(validator).validate(telegramUser);
        verify(tRegService, never()).register(telegramUser);
        assertThat(thrown.getErrors()).containsExactlyInAnyOrderElementsOf(validateMessages);
    }

}