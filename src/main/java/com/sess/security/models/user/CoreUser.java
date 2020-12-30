package com.sess.security.models.user;

import com.sess.security.models.City;
import com.sess.security.models.Sex;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The data of the user for core service
 */
public class CoreUser extends BaseUser {

    private final UUID securityKey;

    public CoreUser(String nickname, String email, City city, Sex sex, LocalDateTime birthday, UUID securityKey) {
        super(nickname, email, city, sex, birthday);
        this.securityKey = securityKey;
    }

    public UUID getSecurityKey() {
        return securityKey;
    }
}
