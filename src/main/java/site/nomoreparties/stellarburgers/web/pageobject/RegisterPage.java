package site.nomoreparties.stellarburgers.web.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.Assert.*;
import static site.nomoreparties.stellarburgers.assist.Url.REGISTER_PAGE_URL;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By registerPageLabel = By.xpath("//h2[contains(text(), 'Регистрация')]");
    private final By nameInput = By.xpath("//label[contains(text(), 'Имя')]/../input[@name='Name']");
    private final By emailInput = By.xpath("//label[contains(text(), 'Email')]/../input[@name='name']");
    private final By passwordInput = By.xpath("//input[@name='Пароль']");
    private final By registerButton = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    private final By passwordErrorText = By.xpath("//p[contains(text(), 'Некорректный пароль')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @Step("Open register page")
    public void openRegisterPage() {
        driver.get(REGISTER_PAGE_URL);
        driver.manage().window().maximize();
    }

    @Step("Check register page is opened by label")
    public void isRegisterPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerPageLabel));
    }

    @Step("Filling user name input")
    public void fillNameInput(String userName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(userName);
    }

    @Step("Filling user email input")
    public void fillEmailInput(String userEmail) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(userEmail);
    }

    @Step("Filling user password input")
    public void fillPasswordInput(String userPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(userPassword);
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton)).click();
    }

    @Step("Check password error text")
    public void checkPasswordErrorText(String expectedErrorText) {
        String actualErrorText = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorText)).getText();
        assertEquals("Текст сообщения об ошибке не соответствует ожидаемому", expectedErrorText, actualErrorText);
    }


}
