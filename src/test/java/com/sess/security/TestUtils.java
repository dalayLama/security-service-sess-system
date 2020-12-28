package com.sess.security;

import com.sess.security.models.City;
import com.sess.security.models.Sex;
import com.sess.security.models.TelegramData;
import com.sess.security.models.user.SecurityUser;
import com.sess.security.models.user.TelegramUser;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestUtils {

    private static long USER_ID = 1;

    private static long TELEGRAM_ID = 1;

    private static int TELEGRAM_KEY_ID = 1;

    public static TelegramUser createTelegramUser() {
        return new TelegramUser(
                String.format("user%d", USER_ID),
                String.format("user%d@mail.ru", USER_ID),
                new City(1L, "city_address"),
                Sex.MALE,
                LocalDateTime.now(),
                TELEGRAM_KEY_ID
        );
    }

    public static SecurityUser createNewSecurityUser(String email, TelegramData telegramData, UUID securityKey) {
        SecurityUser securityUser = new SecurityUser();
        securityUser.setId(null);
        securityUser.setEmail(email);
        securityUser.setTelegramData(telegramData);
        securityUser.setSecurityKey(securityKey);
        telegramData.setUser(securityUser);
        return securityUser;
    }

    public static SecurityUser createNewSecurityUser(TelegramData telegramData) {
        return createNewSecurityUser(
                String.format("user%d@mail.ru", USER_ID),
                telegramData,
                UUID.randomUUID()
        );
    }

    public static SecurityUser createNewSecurityUser() {
        return createNewSecurityUser(createNewTelegramData());
    }

    public static TelegramData createNewTelegramData() {
        TelegramData td = new TelegramData();
        td.setId(null);
        td.setTelegramId(null);
        return td;
    }

    public static SecurityUser createSecurityUser(String email, TelegramData telegramData, UUID securityKey) {
        SecurityUser securityUser = new SecurityUser();
        securityUser.setId(newUserId());
        securityUser.setEmail(email);
        securityUser.setTelegramData(telegramData);
        securityUser.setSecurityKey(securityKey);
        telegramData.setUser(securityUser);
        return securityUser;
    }

    public static SecurityUser createSecurityUser(TelegramData telegramData) {
        return createSecurityUser(
                String.format("user%d@mail.ru", USER_ID),
                telegramData,
                UUID.randomUUID()
        );
    }

    public static SecurityUser createSecurityUser() {
        return createSecurityUser(createTelegramData());
    }

    public static TelegramData createTelegramData(int telegramId) {
        TelegramData td = new TelegramData();
        td.setId(newTelegramId());
        td.setTelegramId(telegramId);
        return td;
    }

    public static TelegramData createTelegramData() {
        return createTelegramData(newTelegramKeyId());
    }

    private static long newUserId() {
        return USER_ID++;
    }

    private static long newTelegramId() {
        return TELEGRAM_ID++;
    }

    private static int newTelegramKeyId() {
        return TELEGRAM_KEY_ID++;
    }

}
