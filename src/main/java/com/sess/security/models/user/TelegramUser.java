package com.sess.security.models.user;

import com.sess.security.models.City;
import com.sess.security.models.Sex;

import java.time.LocalDateTime;

/**
 * The data of the user which come from telegram bot for registration
 */
public class TelegramUser extends BaseUser {

    private final int telegramId;

    public TelegramUser(String nickname, String email, City city, Sex sex, LocalDateTime birthday, int telegramId) {
        super(nickname, email, city, sex, birthday);
        this.telegramId = telegramId;
    }

    public int getTelegramId() {
        return telegramId;
    }
}
