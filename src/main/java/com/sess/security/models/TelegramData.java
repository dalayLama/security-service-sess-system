package com.sess.security.models;

import com.sess.security.models.user.SecurityUser;

public class TelegramData {

    private Long id;

    private SecurityUser user;

    private String key;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SecurityUser getUser() {
        return user;
    }

    public void setUser(SecurityUser user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
