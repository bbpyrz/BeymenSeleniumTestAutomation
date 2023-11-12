package pages;

import methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends Methods {
    Logger logger = LogManager.getLogger(ProductPage.class);

    public CartPage(WebDriver driver) {
        super(driver);
    }
    By productPriceElementInCart=By.cssSelector(".m-productPrice__salePrice");
    By numberOfProductElement=By.id("quantitySelect0-key-0");
    By deleteProduct=By.id("removeCartItemBtn0-key-0");
    By emptyCartCheckElement=By.className("m-empty__messageBtn");
    public void checkPriceOfProduct(String price){
        checkTextForPrice(productPriceElementInCart,price,"Gözlenen Ücret Bilgisi Yanlış");
    }
    public void checkNumberOfProduct(){
        sendKeys(numberOfProductElement,"2 adet");
        if(findElement(numberOfProductElement).getText().equals("1 adet")){
            Assert.fail("İstenilen adette ürün bulunmamaktadır.");
        }
        waitBySeconds(1);
        checkText(numberOfProductElement,"2","adet blgisi doğru değil");
    }
    public void deleteProduct(){
        clickElement(deleteProduct);
    }
    public void checkEmptyCart(){
        isElementVisible(emptyCartCheckElement);
    }
}
