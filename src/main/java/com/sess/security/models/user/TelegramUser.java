package com.sess.security.models.user;

import com.sess.security.models.City;
import com.sess.security.models.Sex;

import java.time.LocalDateTime;

/**
 * The data of the user which come from telegram bot for registration
 */
public class TelegramUser extends BaseUser {

    private final String telegramKey;

    public TelegramUser(String nickname, String email, City city, Sex sex, LocalDateTime birthday, String telegramKey) {
        super(nickname, email, city, sex, birthday);
        this.telegramKey = telegramKey;
    }

    public String getTelegramKey() {
        return telegramKey;
    }
}
