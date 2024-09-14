package com.my;

import com.my.api.UserClient;
import com.my.models.User;
import com.my.pages.MainPage;
import com.my.utils.CredsGenerate;
import com.my.utils.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.my.constants.Const.MAIN_URL;

public class PageTransitionsTest {

    private WebDriver driver;
    private User user;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.getDriver();
        user = CredsGenerate.create();
        UserClient.register(user);

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

    @AfterEach
    public void tearDown() {
        DriverFactory.close(driver);
        UserClient.delete(user);
    }

    @Test
    @DisplayName("Переход в личный кабинет с главной страницы")
    public void transitMainPageToPersonalAccTest() {
        mainPage.
                personalAccountBtnClickWithAuth()
                .waitLoadExitBtnPersAccPage()
                .checkPersAccPage();
    }


    @Test
    @DisplayName("Переход из личного кабинета по кнопке конструктор")
    public void transitionPerAccPageToConstructorTest() {
        mainPage
                .personalAccountBtnClickWithAuth()
                .waitLoadExitBtnPersAccPage()
                .constructorBtnClick()
                .checkMainPage();

    }

    @Test
    @DisplayName("Переход из личного кабинета по логотипу бургера")
    public void transitionPerAccPageToBurgerLogoTest() {
        mainPage
                .personalAccountBtnClickWithAuth()
                .waitLoadExitBtnPersAccPage()
                .logoBtnClick()
                .checkMainPage();

    }

    @Test
    @DisplayName("Переход в раздел булок в конструкторе")
    public void bunTransitionsTest() {
        mainPage
                .saucesBtnClick()
                .bunsBtnClick()
                .checkBunsBtnIsActive();
    }

    @Test
    @DisplayName("Переход в раздел булок в конструкторе")
    public void saucesTransitionsTest() {
        mainPage
                .saucesBtnClick()
                .checkSaucesBtnIsActive();
    }

    @Test
    @DisplayName("Переход в раздел булок в конструкторе")
    public void toppingTransitionsTest() {
        mainPage
                .toppingsBtnClick()
                .checkToppingBtnIsActive();
    }

}
