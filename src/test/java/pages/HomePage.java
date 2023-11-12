
package pages;


import methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utilities.ReadXLSData;

public class HomePage extends Methods {

    Logger logger = LogManager.getLogger(ProductPage.class);
    ReadXLSData readXLSData=new ReadXLSData();
    By rejectCookiesButton =By.xpath("//button[@id='onetrust-reject-all-handler']");
    By closeGenderSectionPopUpButton=By.xpath("//button[@class='o-modal__closeButton bwi-close']//*[name()='svg']");
    By searchBoxElements=By.className("o-header__search--wrapper");
    By searchBoxSendKeysElement =By.id("o-searchSuggestion__input");
    By productSelectionElement=By.className("m-productCard__desc");

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public void home() {
        Assertions.assertEquals("https://www.beymen.com/",driver.getCurrentUrl(),"There is not home page");
    }
    public void acceptCookies(){
        clickElement(rejectCookiesButton);
    }
    public void closeGenderSectionPopUp(){
        clickElement(closeGenderSectionPopUpButton);
    }
    public void sendKeysToSearchBox(int index){
        sendKeysNotClickableElement(searchBoxElements,searchBoxSendKeysElement,readXLSData.getData("ProductNames",index));
    }
    public void clearSearchBox(){
        waitBySeconds(3);
        findElement(searchBoxSendKeysElement).clear();
    }
    public void clickEnterFromKeyboard(){
        findElement(searchBoxSendKeysElement).sendKeys(Keys.ENTER);
    }
    public void clickARandomProduct(){
        selectRandomProduct(productSelectionElement);
    }
}
