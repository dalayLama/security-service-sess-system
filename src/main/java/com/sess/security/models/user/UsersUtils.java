package com.sess.security.models.user;

import java.util.UUID;

public abstract class UsersUtils {

    private UsersUtils() {}

    public static CoreUser createCoreUser(BaseUser baseUser, UUID securityKey) {
        return new CoreUser (
                baseUser.getNickname(),
                baseUser.getEmail(),
                baseUser.getCity(),
                baseUser.getSex(),
                baseUser.getBirthday(),
                securityKey
        );
    }

}
