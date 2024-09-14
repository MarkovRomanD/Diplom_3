package com.my;

import com.my.api.UserClient;
import com.my.models.User;
import com.my.pages.RegisterPage;
import com.my.utils.CredsGenerate;
import com.my.utils.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.my.constants.Const.REGISTER_URL;

public class RegisterTest {

    private WebDriver driver;
    private RegisterPage registerPage;
    private User user;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.getDriver();
        DriverFactory.open(driver, REGISTER_URL);
        registerPage = new RegisterPage(driver);

    }

    @AfterEach
    public void tearDown() {
        DriverFactory.close(driver);
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successRegTest() {

        user = CredsGenerate.create();
        registerPage
                .inputUserEmail(user.getEmail())
                .inputUserName(user.getName())
                .inputUserPassword(user.getPassword())
                .registerBtnClick()
                .waitForLoadLoginBtnLoginPage()
                .checkLoginPage();

        UserClient.delete(user);
    }

    @Test
    @DisplayName("Ошибка с некорректным паролем. Неуспешная регистрация")
    public void regIncorrectPassword() {

        user = CredsGenerate.createPassLen(4);
        registerPage
                .inputUserEmail(user.getEmail())
                .inputUserName(user.getName())
                .inputUserPassword(user.getPassword())
                .registerBtnClick();

        registerPage
                .checkIncorrectPasswordMessage();
    }
}
