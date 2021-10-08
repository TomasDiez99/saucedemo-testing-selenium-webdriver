import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Login successfully")
    public static void loginSuccessfully() {
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.enterPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getTitle(), "PRODUCTS");

        System.out.println("Login terminado piola");

    }

    @Test(testName = "Add one item to cart")
    public static void verifyItemAdded() {
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.enterPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCartBackpack();
        Assert.assertEquals(productsPage.getCardBadge(), "1");
    }

    @Test(testName = "Incorrect Login")
    public static void incorrectLogin() {
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterIncorrectUsername();
        loginForm.enterIncorrectPassword();
        loginForm.pressLoginButton();

        Assert.assertEquals(loginForm.getLoginErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(testName = "Log out")
    public static void logOut() {
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.enterPassword();
        loginForm.pressLoginButton();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.clickLateralMenu();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Must wait menu expand animation
        productsPage.clickLogoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), Utils.BASE_URL);
    }

    @Test(testName = "Verify cart purchase")
    public static void verifyCartPurchase() {
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.enterPassword();
        loginForm.pressLoginButton();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCartBackpack();
        productsPage.clickShoppingCartButton();
        String expectedText = productsPage.getSauceLabsBackpack();

        ShoppingCart shoppingCart = new ShoppingCart(driver);
        Assert.assertEquals(expectedText, shoppingCart.getInventoryItemName());
    }

    @AfterSuite
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
