import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginForm extends PageObject {

    private final String INCORRECT_USERNAME = "incorrectUsernameText";
    private final String INCORRECT_PASSWORD = "incorrectPasswordText";

    private final String USERNAME = "standard_user";
    private final String PASSWORD = "secret_sauce";

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement login_button;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement loginErrorMessage;

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    public String getLoginErrorMessage() {
        return this.loginErrorMessage.getText();
    }

    public void enterUsername() {
        this.username.sendKeys(USERNAME);
    }

    public void enterPassword() {
        this.password.sendKeys(PASSWORD);
    }

    public void pressLoginButton() {
        this.login_button.click();
    }

    public void enterIncorrectUsername() {
        this.username.sendKeys(INCORRECT_USERNAME);
    }

    public void enterIncorrectPassword() {
        this.password.sendKeys(INCORRECT_PASSWORD);
    }
}