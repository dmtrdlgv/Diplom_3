package registerpage;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.api.model.User;
import site.nomoreparties.stellarburgers.api.steps.UserSteps;
import site.nomoreparties.stellarburgers.web.pageobject.RegisterPage;

public class RegisterPageTest {

    private WebDriver driver;
    private User user;
    private final UserSteps userSteps = new UserSteps();
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        registerPage = new RegisterPage(driver);
        user = new User();
        user.fillRandomUserData();
    }



    @After
    public void tearDown() {
        user.setName(null);
        userSteps.setTokensFromResponseToUser(userSteps.loginUser(user), user);
        if (user.getAccessToken() != null) userSteps.deleteUser(user.getAccessToken());
    }
}
