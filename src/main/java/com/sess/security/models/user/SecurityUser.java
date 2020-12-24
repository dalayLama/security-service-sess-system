package com.sess.security.models.user;

import com.sess.security.models.TelegramData;

import java.util.UUID;

/**
 * The user who is registered in the security service
 */
public class SecurityUser {

    private Long id;

    private UUID securityKey;

    private String nickname;

    private String email;

    private TelegramData telegramData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(UUID securityKey) {
        this.securityKey = securityKey;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TelegramData getTelegramData() {
        return telegramData;
    }

    public void setTelegramData(TelegramData telegramData) {
        this.telegramData = telegramData;
    }
}
