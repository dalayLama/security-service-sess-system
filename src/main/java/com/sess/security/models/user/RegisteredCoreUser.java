package com.sess.security.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sess.security.models.City;
import com.sess.security.models.Sex;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data of the user who was registered
 */
public class RegisteredCoreUser extends CoreUser {

    private final long id;

    public RegisteredCoreUser(
            @JsonProperty(value = "id") long id,
            @JsonProperty(value = "nickname") String nickname,
            @JsonProperty(value = "email") String email,
            @JsonProperty(value = "city") City city,
            @JsonProperty(value = "sex") Sex sex,
            @JsonProperty(value = "birthday") LocalDateTime birthday,
            @JsonProperty(value = "securityKey") UUID securityKey) {
        super(nickname, email, city, sex, birthday, securityKey);
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
