package com.my.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.my.constants.Const.INCORRECT_PASS_MSG;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterPage {

    private final WebDriver driver;

    private final By nameInput = By.xpath(".//label[text()='Имя']/parent::div/input");
    private final By emailInput = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By passwordInput = By.xpath(".//input[@type='password']");
    private final By registerBtn = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginLink = By.className("Auth_link__1fOlj");
    private final By errorMessage = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести имя пользователя")
    public RegisterPage inputUserName(String name) {
        this.driver.findElement(nameInput).sendKeys(name);
        return this;
    }

    @Step("Ввести почту пользователя")
    public RegisterPage inputUserEmail(String email) {
        this.driver.findElement(emailInput).sendKeys(email);
        return this;
    }

    @Step("Ввести пароль пользователя")
    public RegisterPage inputUserPassword(String password) {
        this.driver.findElement(passwordInput).sendKeys(password);
        return this;
    }


    @Step("Нажать на кнопку зарегистрироваться")
    public LoginPage registerBtnClick() {
        this.driver.findElement(registerBtn).click();
        return new LoginPage(driver);
    }

    @Step("Нажать на кнопку Логин со страницы регистрации")
    public LoginPage loginBtnClick() {
        this.driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Проверка ввода некорректного пароля")
    public void checkIncorrectPasswordMessage() {
        assertEquals(INCORRECT_PASS_MSG, driver.findElement(errorMessage).getText(), "Не правильно отработала проверка на некорректный пароль");
    }

}
