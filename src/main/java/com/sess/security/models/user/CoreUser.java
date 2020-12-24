package com.sess.security.models.user;

import com.sess.security.models.City;
import com.sess.security.models.Sex;

import java.time.LocalDateTime;

/**
 * The data of the user for core service
 */
public class CoreUser extends BaseUser {

    private final String securityKey;

    public CoreUser(String nickname, String email, City city, Sex sex, LocalDateTime birthday, String securityKey) {
        super(nickname, email, city, sex, birthday);
        this.securityKey = securityKey;
    }

    public String getSecurityKey() {
        return securityKey;
    }
}
