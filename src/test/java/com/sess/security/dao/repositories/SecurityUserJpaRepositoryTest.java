package com.sess.security.dao.repositories;

import com.sess.security.models.TelegramData;
import com.sess.security.models.user.SecurityUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Disabled
class SecurityUserJpaRepositoryTest {

    private static int TELEGRAM_ID = 1;

    @Autowired
    private SecurityUserJpaRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void shouldAddUser() {
        SecurityUser su = createNewUser();

        SecurityUser save = repository.save(su);
        assertThat(save.getId()).isNotNull();
        assertThat(save.getTelegramData().getId()).isNotNull();
    }

    @Test
    public void shouldUpdateUser() {
        SecurityUser saved = repository.save(createNewUser());
        entityManager.flush();

        saved.setEmail(String.format("%s@email.ru", UUID.randomUUID().toString()));
        repository.save(saved);
        entityManager.flush();
    }

    private SecurityUser createNewUser() {
        SecurityUser su = new SecurityUser();
        su.setSecurityKey(UUID.randomUUID());
        su.setEmail(String.format("%s@email.ru", UUID.randomUUID().toString()));

        TelegramData telegramData = new TelegramData();
        telegramData.setTelegramId(TELEGRAM_ID++);
        telegramData.setUser(su);

        su.setTelegramData(telegramData);

        return su;
    }


}