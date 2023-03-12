package site.nomoreparties.stellarburgers.web.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.assist.Url.LOGIN_PAGE_URL;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By loginPageLabel = By.xpath("//h2[contains(text(), 'Вход')]");
    private final By emailInput = By.xpath("//label[contains(text(), 'Email')]/../input[@name='name']");
    private final By passwordInput = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[contains(text(), 'Войти')]");
    private final By registerPageReference = By.xpath("//a[@href='/register']");
    private final By recoveryPageReference = By.xpath("//a[@href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @Step("Open login page")
    public void openRegisterPage() {
        driver.get(LOGIN_PAGE_URL);
        driver.manage().window().maximize();
    }

    @Step("Check by label login page open")
    public void isLoginPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageLabel));
    }

    @Step("Filling user email input")
    public void fillEmailInput(String userEmail) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(userEmail);
    }

    @Step("Filling user password input")
    public void fillPasswordInput(String userPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(userPassword);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();
    }

    @Step("Click register page reference")
    public void clickRegisterPageReference() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerPageReference)).click();
    }

    @Step("Click recovery password page reference")
    public void clickRecoveryPageReference() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(recoveryPageReference)).click();
    }
}
