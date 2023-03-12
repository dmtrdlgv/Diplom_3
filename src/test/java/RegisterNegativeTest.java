import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.assist.RandomGenerator;
import site.nomoreparties.stellarburgers.web.pageobject.RegisterPage;

@RunWith(Parameterized.class)
public class RegisterNegativeTest {

    private final int passwordSize;
    private WebDriver driver;
    private RegisterPage registerPage;

    public RegisterNegativeTest(int passwordSize) {
        this.passwordSize = passwordSize;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {0},
                {1},
                {5}
        };
    }

    @Before
    public void setUp() {
        //Настройка драйвера
        if (System.getProperty("driver_path") != null) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("driver_path"));
        }
        driver = new ChromeDriver();
        registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.checkRegisterPageOpened();
    }

    @Test
    public void registerUser_WithIncorrectPassword_ExpectedError() {
        registerPage.fillAllUserInputs(RandomGenerator.randomName(),
                RandomGenerator.randomEmail(), RandomGenerator.randomPassword(passwordSize));
        registerPage.clickRegisterButton();
        registerPage.checkPasswordErrorText();
    }

    @After
    public void teardown() {
        driver.quit();
        //Удаление пользователя
    }
}
