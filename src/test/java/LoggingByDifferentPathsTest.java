import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.api.model.User;
import site.nomoreparties.stellarburgers.api.steps.UserSteps;
import site.nomoreparties.stellarburgers.web.pageobject.*;

public class LoggingByDifferentPathsTest {

    private final UserSteps userSteps = new UserSteps();
    protected WebDriver driver;
    private User user;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private BuildBurgerPage buildBurgerPage;
    private RecoveryPasswordPage recoveryPage;
    private AccountPage accountPage;

    @Before
    public void setUp() {
        //Настройка драйвера
        if (System.getProperty("driver_path") != null) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("driver_path"));
        }
        driver = new ChromeDriver();
        user = new User();
        user.fillRandomUserData();
        userSteps.setTokensFromResponseToUser(userSteps.registerUser(user), user);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        buildBurgerPage = new BuildBurgerPage(driver);
        recoveryPage = new RecoveryPasswordPage(driver);
        accountPage = new AccountPage(driver);
    }

    @Test
    @DisplayName("Test of logging via main page login button")
    public void login_ViaMainPageButton_ExpectedRedirectToLogin() {
        buildBurgerPage.openBuildBurgerPage();
        buildBurgerPage.clickLoginButton();
        loginPage.checkLoginPageOpen();
        loginPage.fillInputsAndClickLoginButton(user.getEmail(), user.getPassword());
        buildBurgerPage.checkBuildBurgerPageOpened();
        //Проверка что авторизация выполнена
        buildBurgerPage.clickAccountButton();
        accountPage.checkAccountPageOpened();
    }

    @Test
    @DisplayName("Test of logging via account button")
    public void login_ViaAccountButton_ExpectedRedirectToLogin() {
        buildBurgerPage.openBuildBurgerPage();
        buildBurgerPage.clickAccountButton();
        loginPage.checkLoginPageOpen();
        loginPage.fillInputsAndClickLoginButton(user.getEmail(), user.getPassword());
        buildBurgerPage.checkBuildBurgerPageOpened();
        //Проверка что авторизация выполнена
        buildBurgerPage.clickAccountButton();
        accountPage.checkAccountPageOpened();
    }

    @Test
    @DisplayName("Test of logging via registration form")
    public void login_ViaRegisterForm_ExpectedRedirectToLogin() {
        registerPage.openRegisterPage();
        registerPage.clickLoginPageReference();
        loginPage.checkLoginPageOpen();
        loginPage.fillInputsAndClickLoginButton(user.getEmail(), user.getPassword());
        buildBurgerPage.checkBuildBurgerPageOpened();
        //Проверка что авторизация выполнена
        buildBurgerPage.clickAccountButton();
        accountPage.checkAccountPageOpened();
    }

    @Test
    @DisplayName("Test of logging via recovery password form")
    public void login_ViaRecoveryForm_ExpectedRedirectToLogin() {
        recoveryPage.openRecoveryPage();
        recoveryPage.clickLoginPageReference();
        loginPage.checkLoginPageOpen();
        loginPage.fillInputsAndClickLoginButton(user.getEmail(), user.getPassword());
        buildBurgerPage.checkBuildBurgerPageOpened();
        //Проверка что авторизация выполнена
        buildBurgerPage.clickAccountButton();
        accountPage.checkAccountPageOpened();
    }

    @After
    public void teardown() {
        driver.quit();
        //Удаление пользователя
        user.setName(null);
        userSteps.setTokensFromResponseToUser(userSteps.loginUser(user), user);
        if (user.getAccessToken() != null) userSteps.deleteUser(user.getAccessToken());
    }
}
