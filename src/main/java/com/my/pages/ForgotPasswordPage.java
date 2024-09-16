package com.my.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }


    private final By loginLink = By.xpath(".//a[text()='Войти']");

    @Step("Нажать на кнопку войти на странице восстановления пароля")
    public LoginPage loginBtnClick(){
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
}
