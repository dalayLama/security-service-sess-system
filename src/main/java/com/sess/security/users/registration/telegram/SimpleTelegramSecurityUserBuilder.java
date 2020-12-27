package com.sess.security.users.registration.telegram;

import com.sess.security.models.TelegramData;
import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.SecurityKeyGenerator;
import org.springframework.stereotype.Component;

@Component
public class SimpleTelegramSecurityUserBuilder implements TelegramSecurityUserBuilder {

    private final SecurityKeyGenerator keyGenerator;

    public SimpleTelegramSecurityUserBuilder(SecurityKeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @Override
    public SecurityUser generateNewSecurityUser(TelegramUser tUser) {
        SecurityUser su = new SecurityUser();
        su.setSecurityKey(keyGenerator.generate());
        su.setEmail(tUser.getEmail());

        TelegramData td = new TelegramData();
        td.setUser(su);
        td.setTelegramId(tUser.getTelegramId());

        su.setTelegramData(td);
        return su;
    }
}
