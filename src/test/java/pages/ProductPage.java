package pages;

import methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WriteDataToTXT;

import java.util.List;

public class ProductPage extends Methods {
    Logger logger = LogManager.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    By productInfoElement=By.className("o-productDetail__description");
    By productPriceElement=By.id("priceNew");
    String price;
    By sizeSelectButton=By.className("m-variation__item");
    By addToCartButton=By.id("addBasket");
    By goToCartButton=By.xpath("//a[@title='Sepetim']//*[name()='svg']");

    By elementClicked=By.cssSelector("div[id='sizes'] label[class='m-form__label m-variation__label']");


    public void takeProductPriceAndInfo(){
        WriteDataToTXT writeDataToTXT=new WriteDataToTXT();
        String[] data =new String[2];
        data[0]=getText(productInfoElement);
        data[1]=getText(productPriceElement);
        writeDataToTXT.writeData(data);
        String split[]=findElement(productPriceElement).getText().split(" ");
        this.price=split[0];
    }
    public String getPrice(){
        return price;
    }
    public void addToCart(){
        List<WebElement> products = driver.findElements(sizeSelectButton);
        WebElement element;
        for(int i=1;i<=products.size();i++){
            element = products.get(i-1);
            getJSExecutor().executeScript("arguments[0].click();", element);
            if(!findElement(elementClicked).getText().equals("Beden Beden Seçiniz")){
                break;
            }
        }
        findElement(addToCartButton).click();
        logger.info("Ürün bedeni, seçildi ve sepete eklendi...");
    }
    public void goToCart(){
        waitBySeconds(5);
        scrollWithAction(goToCartButton);
        findElement(goToCartButton).click();
        logger.info("Sepete gidildi...");
    }
}
