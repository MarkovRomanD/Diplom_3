package com.my.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.my.constants.Const.LOGIN_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginBtn = By.xpath(".//button[text()='Войти']");
    private final By emailInput = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By passwordInput = By.xpath(".//input[@type='password']");


    @Step("Ввести email юзера")
    public LoginPage emailInputSendKeys(String email) {
        driver.findElement(emailInput).sendKeys(email);
        return this;
    }

    @Step("Ввести пароль юзера")
    public LoginPage passInputSendKeys(String pass) {
        driver.findElement(passwordInput).sendKeys(pass);
        return this;
    }

    @Step("Нажать на кнопку войти")
    public MainPage loginBtnClick() {
        driver.findElement(loginBtn).click();
        return new MainPage(driver);
    }

    @Step("Ожидание кнопки войти на странице логина")
    public LoginPage waitForLoadLoginBtnLoginPage() {
        (new WebDriverWait(this.driver, Duration.ofSeconds(15L))).until((driver) -> {
            return driver.findElement(this.loginBtn).getText() != null;
        });

        return this;
    }

    @Step("Проверка перехода на страницу логина")
    public LoginPage checkLoginPage() {
        assertEquals(LOGIN_URL, driver.getCurrentUrl(), "Переход на страницу логина не произошёл");
        return this;
    }

}
