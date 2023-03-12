package site.nomoreparties.stellarburgers.web.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.assist.Url.REGISTER_PAGE_URL;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By registerPageLabel = By.xpath("//h2[contains(text(), 'Регистрация')]"); //локатор текста Регистраци
    private final By nameInput = By.xpath("//label[contains(text(), 'Имя')]/../input[@name='name']"); //локатор инпута Имя
    private final By emailInput = By.xpath("//label[contains(text(), 'Email')]/../input[@name='name']"); //локатор инпута Email
    private final By passwordInput = By.xpath("//input[@name='Пароль']"); //локатор инпута Пароль
    private final By registerButton = By.xpath("//button[contains(text(), 'Зарегистрироваться')]"); //кнопка Зарегистрироваться
    private final By passwordErrorText = By.xpath("//p[contains(text(), 'Некорректный пароль')]"); //тест ошибки "Некорректный пароль"
    private final By loginPageReference = By.xpath("//a[@href='/login']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @Step("Open register page")
    public void openRegisterPage() {
        driver.get(REGISTER_PAGE_URL);
        driver.manage().window().maximize();
    }

    @Step("Check by label register page open")
    public void checkRegisterPageOpened() {
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

    @Step("Common step filling user name, email, password")
    public void fillAllUserInputs(String name, String email, String password) {
        fillNameInput(name);
        fillEmailInput(email);
        fillPasswordInput(password);
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton)).click();
    }

    @Step("Check password error text")
    public void checkPasswordErrorText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorText));
    }

    @Step("Click login page reference")
    public void clickLoginPageReference() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageReference)).click();
    }
}
