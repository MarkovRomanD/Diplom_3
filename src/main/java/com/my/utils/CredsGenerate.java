package com.my.utils;

import com.my.models.User;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class CredsGenerate {

    @Step("Генерация данных корректного случайного пользователя")
    public static User create() {
        return new User(
                createEmail(),
                createString(),
                createString()
        );
    }

    @Step("Генерация данных случайного пользователя с паролем выбранной длины")
    public static User createPassLen(int passwordLength) {
        return new User(
                createEmail(),
                createString(passwordLength),
                createString()
        );
    }

    public static String createEmail() {
        return String.format(
                "%s@%s.%s",
                RandomStringUtils.random(10, true, false).toLowerCase(),
                RandomStringUtils.random(3, true, false).toLowerCase(),
                RandomStringUtils.random(3, true, false).toLowerCase());
    }

    public static String createString() {
        return RandomStringUtils.random(10, true, false).toLowerCase();
    }

    public static String createString(int length) {
        return RandomStringUtils.random(length, true, false).toLowerCase();
    }
}
