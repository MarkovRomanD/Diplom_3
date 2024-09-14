package com.my;

import com.my.api.UserClient;
import com.my.models.User;
import com.my.pages.ForgotPasswordPage;
import com.my.pages.MainPage;
import com.my.pages.RegisterPage;
import com.my.utils.CredsGenerate;
import com.my.utils.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.my.constants.Const.*;

public class LoginTest {

    private WebDriver driver;
    private User user;
    private MainPage mainPage;


    @BeforeEach
    public void setUp() {
        driver = DriverFactory.getDriver();
        user = CredsGenerate.create();
        UserClient.register(user);
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.close(driver);
        UserClient.delete(user);
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void loginMainPageTest() {
        DriverFactory.open(driver, MAIN_URL);
        mainPage = new MainPage(driver);
        mainPage
                .loginBtnClick()
                .waitForLoadLoginBtnLoginPage()
                .emailInputSendKeys(user.getEmail())
                .passInputSendKeys(user.getPassword())
                .loginBtnClick()
                .waitForLoadHeaderMainPage()
                .checkMainPage();
    }

    @Test
    @DisplayName("вход через кнопку личного кабинета")
    public void loginMainPagePersonalAccountBtnTest() {
        DriverFactory.open(driver, MAIN_URL);
        mainPage = new MainPage(driver);
        mainPage
                .personalAccountBtnClickNoAuth()
                .waitForLoadLoginBtnLoginPage()
                .emailInputSendKeys(user.getEmail())
                .passInputSendKeys(user.getPassword())
                .loginBtnClick()
                .waitForLoadHeaderMainPage()
                .checkMainPage();
    }

    @Test
    @DisplayName("вход через кнопку логина в форме регистрации")
    public void loginFromRegisterPageLoginBtnTest() {
        DriverFactory.open(driver, REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage
                .loginBtnClick()
                .waitForLoadLoginBtnLoginPage()
                .emailInputSendKeys(user.getEmail())
                .passInputSendKeys(user.getPassword())
                .loginBtnClick()
                .waitForLoadHeaderMainPage()
                .checkMainPage();
    }

    @Test
    @DisplayName("вход через кнопку логина в форме восстановления пароля")
    public void loginFromForgotPasswordPageTest() {
        DriverFactory.open(driver, FORGOT_PASS_URL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage
                .loginBtnClick()
                .waitForLoadLoginBtnLoginPage()
                .emailInputSendKeys(user.getEmail())
                .passInputSendKeys(user.getPassword())
                .loginBtnClick()
                .waitForLoadHeaderMainPage()
                .checkMainPage();
    }

}
