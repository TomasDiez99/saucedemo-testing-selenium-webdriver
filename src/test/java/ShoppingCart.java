import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCart extends PageObject {
    public ShoppingCart(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "inventory_item_name")
    private WebElement inventoryItemName;

    public String getInventoryItemName() {
        return inventoryItemName.getText();
    }
}
