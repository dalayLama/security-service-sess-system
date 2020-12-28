package com.sess.security.users.registration.telegram;

import com.sess.security.TestUtils;
import com.sess.security.dao.repositories.SecurityUserJpaRepository;
import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.exceptions.ValidateException;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class JpaTelegramRegistrationServiceTest {

    private static final SecurityUser SECURITY_USER = TestUtils.createSecurityUser();

    @Test
    public void shouldExecuteMethodInCorrectOrder() {
        SecurityUserJpaRepository repository = mock(SecurityUserJpaRepository.class);
        TelegramSecurityUserBuilder userBuilder = mock(TelegramSecurityUserBuilder.class);
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(repository.findByTelegramDataId(anyInt())).thenReturn(Optional.empty());
        when(userBuilder.generateNewSecurityUser(any(TelegramUser.class))).thenReturn(SECURITY_USER);
        when(repository.save(any(SecurityUser.class))).thenReturn(SECURITY_USER);

        TelegramUser telegramUser = TestUtils.createTelegramUser();

        JpaTelegramRegistrationService service = new JpaTelegramRegistrationService(repository, userBuilder);
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
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(SECURITY_USER));
        when(repository.findByTelegramDataId(anyInt())).thenReturn(Optional.of(SECURITY_USER));

        JpaTelegramRegistrationService service = new JpaTelegramRegistrationService(repository, userBuilder);
        Set<String> expectedMessages = Set.of(
                service.getMessageViolationUniqueEmail(),
                service.getMessageViolationUniqueTelegramId()
        );

        TelegramUser telegramUser = TestUtils.createTelegramUser();

        ValidateException thrown = assertThrows(
                ValidateException.class,
                () -> service.register(telegramUser)
        );

        assertThat(thrown.getErrors()).containsExactlyInAnyOrderElementsOf(expectedMessages);
    }

}