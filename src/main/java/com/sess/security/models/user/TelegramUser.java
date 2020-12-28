package com.sess.security.models.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sess.security.models.City;
import com.sess.security.models.Sex;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * The data of the user which come from telegram bot for registration
 */
public class TelegramUser extends BaseUser {

    @Min(value = 1, message = "Идентификатор telegram-а не может быть меньше 0")
    private final int telegramId;

    @JsonCreator
    public TelegramUser(
            @JsonProperty(value = "nickname") String nickname,
            @JsonProperty(value = "email") String email,
            @JsonProperty(value = "city") City city,
            @JsonProperty(value = "sex") Sex sex,
            @JsonProperty(value = "birthday") LocalDateTime birthday,
            @JsonProperty(value = "telegramId") int telegramId) {
        super(nickname, email, city, sex, birthday);
        this.telegramId = telegramId;
    }

    public int getTelegramId() {
        return telegramId;
    }
}
