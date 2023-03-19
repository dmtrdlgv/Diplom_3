import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.api.model.User;
import site.nomoreparties.stellarburgers.api.steps.UserSteps;
import site.nomoreparties.stellarburgers.web.pageobject.AccountPage;
import site.nomoreparties.stellarburgers.web.pageobject.BuildBurgerPage;
import site.nomoreparties.stellarburgers.web.pageobject.LoginPage;

public class LogoutTest {
    private static final UserSteps userSteps = new UserSteps();
    protected WebDriver driver;
    private User user;
    private LoginPage loginPage;
    private BuildBurgerPage buildBurgerPage;
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
        loginPage = new LoginPage(driver);
        buildBurgerPage = new BuildBurgerPage(driver);
        accountPage = new AccountPage(driver);
        loginPage.openLoginPage();
        loginPage.fillInputsAndClickLoginButton(user.getEmail(), user.getPassword());
        buildBurgerPage.checkBuildBurgerPageOpened();
    }

    @Test
    @DisplayName("Test of logout from account page by logout button")
    public void logout_ByAccountLogoutButton_ExpectedLoginPage() {
        buildBurgerPage.clickAccountButton();
        accountPage.clickLogoutButton();
        loginPage.checkLoginPageOpen();
        //Проверка что пользователь не авторизован
        loginPage.clickAccountButton();
        loginPage.checkLoginPageOpen();
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
