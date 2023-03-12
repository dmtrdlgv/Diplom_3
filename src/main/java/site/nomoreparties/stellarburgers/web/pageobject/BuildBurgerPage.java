package site.nomoreparties.stellarburgers.web.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static site.nomoreparties.stellarburgers.assist.Url.BASE_URL;

public class BuildBurgerPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By buildBurgerPageLabel = By.xpath("//h1[contains(text(), 'Соберите бургер')]"); //Заголовок страницы
    private final By bunTab = By.xpath("//span[contains(text(), 'Булки')]/.."); //кнопка вкладки Булки
    private final By sauceTab = By.xpath("//span[contains(text(), 'Соусы')]/.."); //кнопка вкладки Соусы
    private final By fillingTab = By.xpath("//span[contains(text(), 'Начинки')]/.."); //кнопка вкладки Начинки
    private final By accountButton = By.xpath("//a[@href='/account']"); //Кнопка входа в Личный кабинет
    private final By loginButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]"); //Кнопка входа в аккаунт
    private String attribute;

    public BuildBurgerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @Step("Open build burger page")
    public void openBuildBurgerPage() {
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @Step("Check by label build burger page open")
    public void checkBuildBurgerPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(buildBurgerPageLabel));
    }

    @Step("Click bun tab")
    public void clickBunTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunTab)).click();
    }

    @Step("Check bun tab switch")
    public void checkBunTabSwitch() {
        attribute = wait.until(ExpectedConditions.visibilityOfElementLocated(bunTab)).getAttribute("class");
        assertTrue(attribute.contains("current"));
    }

    @Step("Click sauce tab")
    public void clickSauceTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sauceTab)).click();
    }

    @Step("Check sauce tab switch")
    public void checkSauceTabSwitch() {
        attribute = wait.until(ExpectedConditions.visibilityOfElementLocated(sauceTab)).getAttribute("class");
        assertTrue(attribute.contains("current"));
    }

    @Step("Click filling tab")
    public void clickFillingTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillingTab)).click();
    }

    @Step("Check filling tab switch")
    public void checkFillingTabSwitch() {
        attribute = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingTab)).getAttribute("class");
        assertTrue(attribute.contains("current"));
    }

    @Step("Click account button")
    public void clickAccountButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountButton)).click();
    }

    @Step("Click login button")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();
    }
}
