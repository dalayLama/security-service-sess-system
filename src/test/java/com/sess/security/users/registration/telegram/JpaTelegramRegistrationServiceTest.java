package com.sess.security.users.registration.telegram;

import com.sess.security.TestUtils;
import com.sess.security.components.message.MessageId;
import com.sess.security.components.message.MessageService;
import com.sess.security.dao.repositories.SecurityUserJpaRepository;
import com.sess.security.exceptions.ErrorMessage;
import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.ValidateException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class JpaTelegramRegistrationServiceTest {

    private static final SecurityUser SECURITY_USER = TestUtils.createSecurityUser();

    @Test
    public void shouldExecuteMethodInCorrectOrder() {
        SecurityUserJpaRepository repository = mock(SecurityUserJpaRepository.class);
        TelegramSecurityUserBuilder userBuilder = mock(TelegramSecurityUserBuilder.class);
        MessageService messageService = mock(MessageService.class);

        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(repository.findByTelegramDataId(anyInt())).thenReturn(Optional.empty());
        when(userBuilder.generateNewSecurityUser(any(TelegramUser.class))).thenReturn(SECURITY_USER);
        when(repository.save(any(SecurityUser.class))).thenReturn(SECURITY_USER);

        TelegramUser telegramUser = TestUtils.createTelegramUser();

        JpaTelegramRegistrationService service = new JpaTelegramRegistrationService(repository, userBuilder, messageService);
        SecurityUser saved = service.register(telegramUser);

        verify(repository).findByEmail(telegramUser.getEmail());
        verify(repository).findByTelegramDataId(telegramUser.getTelegramId());
        verify(userBuilder).generateNewSecurityUser(telegramUser);
        verify(repository).save(SECURITY_USER);
        assertThat(saved).isSameAs(SECURITY_USER);
    }

    @Test
    public void shouldThrowValidateException() {
        SecurityUserJpaRepository repository = mock(SecurityUserJpaRepository.class);
        TelegramSecurityUserBuilder userBuilder = mock(TelegramSecurityUserBuilder.class);
        List<ErrorMessage> expectedMessages = List.of(
                new ErrorMessage("1", "message 1"),
                new ErrorMessage("2", "message 2")
        );
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(SECURITY_USER));
        when(repository.findByTelegramDataId(anyInt())).thenReturn(Optional.of(SECURITY_USER));
        MessageService messageService = mock(MessageService.class);
        when(messageService.sayError(MessageId.UNIQUE_EMAIL_VIOLATION)).thenReturn(
                expectedMessages.get(0)
        );
        when(messageService.sayError(MessageId.UNIQUE_TELEGRAM_ID_VIOLATION)).thenReturn(
                expectedMessages.get(1)
        );

        JpaTelegramRegistrationService service = new JpaTelegramRegistrationService(repository, userBuilder, messageService);

        TelegramUser telegramUser = TestUtils.createTelegramUser();

        ValidateException thrown = assertThrows(
                ValidateException.class,
                () -> service.register(telegramUser)
        );

        verify(messageService).sayError(MessageId.UNIQUE_EMAIL_VIOLATION);
        verify(messageService).sayError(MessageId.UNIQUE_TELEGRAM_ID_VIOLATION);
        verify(userBuilder, never()).generateNewSecurityUser(telegramUser);
        verify(repository, never()).save(any());
        assertThat(thrown.getError().isResultOperation()).isFalse();
        List<ErrorMessage> messages = new ArrayList<>(thrown.getError().getMessages());
        assertThat(messages).containsExactlyInAnyOrderElementsOf(expectedMessages);
    }

}