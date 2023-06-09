package site.nomoreparties.stellarburgers.web.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By accountPage = By.xpath("//main/div[contains(@class, 'Account')]"); //локатор страницы Личного кабинета
    private final By logoutButton = By.xpath("//button[contains(text(), 'Выход')]"); //Кнопка Выхода из аккаунта
    private final By constructorButton = By.xpath("//p[contains(text(), 'Конструктор')]"); //Кнопка Конструтор в шапке
    private final By logo = By.xpath("//div[contains(@class, 'logo')]"); // Логотип в шапке

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @Step("Check by label account page open")
    public void checkAccountPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountPage));
    }

    @Step("Click logout button")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).click();
    }

    @Step("Click constructor button")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(constructorButton)).click();
    }

    @Step("Click logo")
    public void clickLogo() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).click();
    }
}
