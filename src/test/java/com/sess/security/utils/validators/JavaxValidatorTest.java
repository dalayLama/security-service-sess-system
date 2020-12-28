package com.sess.security.utils.validators;

import com.sess.security.models.City;
import com.sess.security.models.Sex;
import com.sess.security.models.user.BaseUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.utils.converters.MessageConstraintConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class JavaxValidatorTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = new JavaxValidator(factory.getValidator(), new MessageConstraintConverter());
    }

    @Test
    public void listErrorsShouldBeEmpty() {
        BaseUser baseUser = new BaseUser("test1", "test@mail.ru",
                new City(1L, "address"), Sex.MALE, LocalDateTime.now());
        Set<String> errors = validator.validate(baseUser);
        assertThat(errors).isEmpty();
    }

    @Test
    public void listErrorsShouldBeContainsFollowingErrors() {
        Set<String> expectedErrors = Set.of(
                "Поле \"Ник\" обязательно для заполнения",
                "Поле \"email\" обязательно для заполнения",
                "Поле \"Город\" обязательно для заполнения",
                "Поле \"Пол\" обязательно для заполнения",
                "Поле \"Дата рождения\" обязательно для заполнения"
        );
        BaseUser baseUser = new BaseUser("", null, null, null, null);
        Set<String> result = validator.validate(baseUser);
        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedErrors);
    }

    @Test
    public void listErrorsShouldBeContainsEmailFormatError() {
        Set<String> expectedErrors = Set.of(
                "Некорректный формат ввода поля \"email\""
        );
        BaseUser baseUser = new BaseUser("test1", "testdwdfe",
                new City(1L, "address"), Sex.MALE, LocalDateTime.now());
        Set<String> result = validator.validate(baseUser);
        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedErrors);
    }

    @Test
    public void listErrorsShouldContainTelegramIdError() {
        Set<String> expectedErrors = Set.of(
                "Идентификатор telegram-а не может быть меньше 0"
        );
        TelegramUser telegramUser = new TelegramUser("test1", "test@mail.ru",
                new City(1L, "address"), Sex.MALE, LocalDateTime.now(), 0);
        Set<String> result = validator.validate(telegramUser);
        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedErrors);
    }

    @Test
    public void listErrorsShouldNotContainTelegramIdError() {
        TelegramUser telegramUser = new TelegramUser("test1", "test@mail.ru",
                new City(1L, "address"), Sex.MALE, LocalDateTime.now(), 1);
        Set<String> result = validator.validate(telegramUser);
        assertThat(result).isEmpty();
    }

}