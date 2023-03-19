import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.api.model.User;
import site.nomoreparties.stellarburgers.api.steps.UserSteps;
import site.nomoreparties.stellarburgers.web.pageobject.LoginPage;
import site.nomoreparties.stellarburgers.web.pageobject.RegisterPage;

public class RegisterTest {

    private final UserSteps userSteps = new UserSteps();
    private User user;
    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private String driver_path;

    @Before
    public void setUp() {
        //Настройка драйвера
        if (System.getProperty("driver_path") != null) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("driver_path"));
        }
        driver = new ChromeDriver();
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        user = new User();
        user.fillRandomUserData();
    }

    @Test
    @DisplayName("Base test success user registration")
    public void registerUser_WithCorrectData_ExpectedSuccess() {
        registerPage.openRegisterPage();
        registerPage.fillAllUserInputs(user.getName(), user.getEmail(), user.getPassword());
        registerPage.clickRegisterButton();
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
