package com.my.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.my.constants.Const.PERSONAL_ACCOUNT_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalAccountPage {
    private final WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By exitBtn = By.xpath(".//button[text()='Выход']");
    private final By constructorBtn = By.xpath(".//p[text()='Конструктор']/parent::a");
    private final By logoBtn = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");

    @Step("Ожидание прогрузки кнопки Выход на странице личного кабинета")
    public PersonalAccountPage waitLoadExitBtnPersAccPage() {
        (new WebDriverWait(this.driver, Duration.ofSeconds(15L))).until((driver) -> {
            return driver.findElement(this.exitBtn).getText() != null;
        });
        return this;
    }


    @Step("Перейти по кнопке конструктор")
    public MainPage constructorBtnClick() {
        driver.findElement(constructorBtn).click();
        return new MainPage(driver);
    }

    @Step("Перейти по лого бургера")
    public MainPage logoBtnClick() {
        driver.findElement(logoBtn).click();
        return new MainPage(driver);
    }

    @Step("Разлогониться по кнопке выход")
    public LoginPage logoutBtnClick() {
        driver.findElement(exitBtn).click();
        return new LoginPage(driver);
    }

    @Step("Проверка перехода на cтраницу личного кабинета")
    public PersonalAccountPage checkPersAccPage() {
        assertEquals(PERSONAL_ACCOUNT_URL, driver.getCurrentUrl(), "Переход на страницу ЛК не произошёл");
        return this;
    }
}
