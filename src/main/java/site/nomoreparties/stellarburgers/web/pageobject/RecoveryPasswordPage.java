package site.nomoreparties.stellarburgers.web.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static site.nomoreparties.stellarburgers.assist.Url.RECOVERY_PASSWORD_PAGE_URL;

public class RecoveryPasswordPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By recoveryPageLabel = By.xpath("//h2[contains(text(), 'Восстановление пароля')]"); //Локатор страницы восстановления пароля
    private final By loginPageReference = By.xpath("//a[@href='/login']"); //Кнопка Войти

    public RecoveryPasswordPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Open recovery page")
    public void openRecoveryPage() {
        driver.get(RECOVERY_PASSWORD_PAGE_URL);
        driver.manage().window().maximize();
    }

    @Step("Check by label recovery page open")
    public void isRecoveryPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(recoveryPageLabel));
    }

    @Step("Click login page reference")
    public void clickLoginPageReference() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageReference)).click();
    }
}
