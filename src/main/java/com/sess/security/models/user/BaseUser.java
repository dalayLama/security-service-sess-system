package com.sess.security.models.user;

import com.sess.security.models.City;
import com.sess.security.models.Sex;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * The base data of the user which is completed by user-input
 */
public class BaseUser {

    @NotBlank(message = "Поле \"Ник\" обязательно для заполнения")
    @Size(max = 128, message = "Поле \"Ник\" не может превышать 128 символов")
    private final String nickname;

    @NotBlank(message = "Поле \"email\" обязательно для заполнения")
    @Email(message = "Некорректный формат ввода поля \"email\"")
    @Size(max = 128, message = "Поле \"email\" не может превышать 128 символов")
    private final String email;

    @NotNull(message = "Поле \"Город\" обязательно для заполнения")
    private final City city;

    @NotNull(message = "Поле \"Пол\" обязательно для заполнения")
    private final Sex sex;

    @NotNull(message = "Поле \"Дата рождения\" обязательно для заполнения")
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
