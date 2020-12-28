package com.sess.security.users.registration.telegram;

import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;

public interface TelegramSecurityUserBuilder {

    SecurityUser generateNewSecurityUser(TelegramUser tUser);

}
