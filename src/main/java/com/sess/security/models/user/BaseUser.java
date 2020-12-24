package com.sess.security.models.user;

import com.sess.security.models.City;
import com.sess.security.models.Sex;

import java.time.LocalDateTime;

/**
 * The base data of the user which is completed by user-input
 */
public class BaseUser {

    private final String nickname;

    private final String email;

    private final City city;

    private final Sex sex;

    private final LocalDateTime birthday;

    public BaseUser(String nickname, String email, City city, Sex sex, LocalDateTime birthday) {
        this.nickname = nickname;
        this.email = email;
        this.city = city;
        this.sex = sex;
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public City getCity() {
        return city;
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }
}
