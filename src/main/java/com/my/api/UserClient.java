package com.my.api;

import com.my.models.AuthUser;
import com.my.models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.my.constants.Const.*;
import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Регистрация пользователя")
    public static void register(User user) {
        given()
                .contentType("application/json")
                .baseUri(BASE_URI)
                .body(user)
                .post(REGISTER_USER_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }

    @Step("Удаление пользователя")
    public static void delete(User user) {
        String accessToken = login(user).as(AuthUser.class).getAccessToken();
        given()
                .headers("Authorization", accessToken)
                .baseUri(BASE_URI)
                .delete(USER_URL)
                .then()
                .statusCode(202);
    }

    @Step("Логин пользователя")
    public static Response login(User user) {
        return given()
                .contentType("application/json")
                .baseUri(BASE_URI)
                .body(user)
                .post(LOGIN_USER_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }
}
