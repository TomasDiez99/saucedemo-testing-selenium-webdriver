import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends PageObject {

    @FindBy(xpath = "//span[contains(text(),'Products')]")
    private WebElement products_label;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCart_SauceBackpack;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    private WebElement cart_badge;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement shoppingCartButton;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement lateralMenuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(id = "item_4_title_link")
    private WebElement sauceLabsBackpack;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getSauceLabsBackpack() {
        String text = sauceLabsBackpack.findElement(By.ByClassName.className("inventory_item_name")).getText();
        System.out.println("El texto de la mochila es: " + text);
        return text;
    }

    public String getTitle() {
        return this.products_label.getText();
    }

    public void clickLateralMenu() {
        this.lateralMenuButton.click();
    }

    public void clickLogoutButton() {
        this.logoutButton.click();
    }

    public void clickShoppingCartButton() {
        this.shoppingCartButton.click();
    }

    public void addToCartBackpack() {
        this.addToCart_SauceBackpack.click();
    }

    public String getCardBadge() {
        return this.cart_badge.getText();
    }
}