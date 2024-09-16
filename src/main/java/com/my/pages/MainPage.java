package com.my.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.my.constants.Const.MAIN_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By personalAccountLink = By.xpath(".//p[text()='Личный Кабинет']/parent::a");
    private final By loginBtn = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By header = By.className("BurgerIngredients_ingredients__1N8v2");
    private final By bunsBtn = By.xpath(".//span[text()='Булки']/parent::div");
    private final By saucesBtn = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By toppingsBtn = By.xpath(".//span[text()='Начинки']/parent::div");


    @Step("Переход по кнопке войти в аккаунт на главной")
    public LoginPage loginBtnClick() {
        driver.findElement(loginBtn).click();
        return new LoginPage(driver);
    }

    @Step("Переход по кнопке личного кабинета")
    public LoginPage personalAccountBtnClickNoAuth() {
        driver.findElement(personalAccountLink).click();
        return new LoginPage(driver);
    }

    @Step("Переход по кнопке личного кабинета")
    public PersonalAccountPage personalAccountBtnClickWithAuth() {
        driver.findElement(personalAccountLink).click();
        return new PersonalAccountPage(driver);
    }

    @Step("Проверка перехода на главную страницу")
    public MainPage checkMainPage() {
        assertEquals(MAIN_URL, driver.getCurrentUrl(), "Переход на главную страницу не произошёл");
        return this;
    }

    @Step("Ожидание хедера главной страницы")
    public MainPage waitForLoadHeaderMainPage() {
        (new WebDriverWait(this.driver, Duration.ofSeconds(15L))).until((driver) -> {
            return driver.findElement(this.header).getText() != null;
        });

        return this;
    }

    @Step("Нажать на кнопку булок в конструкторе")
    public MainPage bunsBtnClick() {
        driver.findElement(bunsBtn).click();
        return this;
    }

    @Step("Нажать на кнопку соусы в конструкторе")
    public MainPage saucesBtnClick() {
        driver.findElement(saucesBtn).click();
        return this;
    }

    @Step("Нажать на кнопку начинок в конструкторе")
    public MainPage toppingsBtnClick() {
        driver.findElement(toppingsBtn).click();
        return this;
    }

    @Step("Проверить, что выбран раздел булок")
    public void checkBunsBtnIsActive() {
        assertTrue(driver.findElement(bunsBtn).getAttribute("class").contains("current"));
    }

    @Step("Проверить, что выбран раздел соусов")
    public MainPage checkSaucesBtnIsActive() {
        assertTrue(driver.findElement(saucesBtn).getAttribute("class").contains("current"));
        return this;
    }

    @Step("Проверить, что выбран раздел начинки")
    public MainPage checkToppingBtnIsActive() {
        assertTrue(driver.findElement(toppingsBtn).getAttribute("class").contains("current"));
        return this;
    }
}
