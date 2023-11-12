package methods;

import driver.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Methods extends BaseTest {

    private static final Logger logger = LogManager.getLogger(Methods.class);
    protected WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public Methods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        this.actions=new Actions(driver);
    }
    protected JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }
    public static void waitBySeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            logger.info(e.getMessage(), e);
        }
    }
    public void waitUntilPresence(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitUntilElementClickable(By by) {
        logger.info("elementin tıklanabilir olması bekleniyor");
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    protected void scrollTo(int x, int y) {
        String jsStmt = String.format("window.scrollTo(%d,%d);", x, y);
        getJSExecutor().executeScript(jsStmt, true);
        logger.info("Sayfa elementin bulunduğu konuma scroll edildi");
    }
    public void scrollWithAction(By element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(element)).build().perform();
    }

    public void clickElement(By by) {
        waitUntilPresence(by);
        WebElement element = findElement(by);
        if (!element.isDisplayed()) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY());
        }
        try {
            waitUntilElementClickable(by);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            Assert.fail("Element tıklanabilir değil!");
        }
        element.click();
    }

    public void sendKeys(By by, String text) {

        waitUntilPresence(by);
        WebElement element = findElement(by);
        if (!element.isDisplayed()) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY());
        }
        waitUntilElementClickable(by);
        element.sendKeys(text);
        logger.info("İlgili elemente " + text + " değeri gönderildi...");
    }
    public void sendKeysNotClickableElement(By key,String text){
        WebElement element=findElement(key);
        actions.moveToElement(element).click().build().perform();
        waitBySeconds(2);
        sendKeys(key,text);
    }
    public void sendKeysNotClickableElement(By key,By key2,String text){
        WebElement element=findElement(key);
        actions.moveToElement(element).click().build().perform();
        waitBySeconds(2);
        sendKeys(key2,text);
    }

    public boolean isElementVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            logger.info("Aranan element görünür");
            return true;
        } catch (Exception e) {
            logger.info("False" + e.getMessage());
            return false;
        }
    }

    public String getAttribute(By by, String attributeName) {
        waitUntilPresence(by);
        return findElement(by).getAttribute(attributeName);
    }

    public String getText(By by) {
        waitUntilPresence(by);
        return findElement(by).getText();
    }

    public String getValue(By by) {
        waitUntilPresence(by);
        return getJSExecutor().executeScript("return arguments[0].value;", findElement(by)).toString();
    }

    public void checkText(By key,String expectedText,String textInfo){
        waitUntilPresence(key);
        Assertions.assertEquals(expectedText,findElement(key).getAttribute("value"),textInfo+" Value is not correct");

    }
    public void checkTextForPrice(By key,String expectedText,String textInfo){
        waitUntilPresence(key);
        String split[]=findElement(key).getText().split(",");
        Assertions.assertEquals(expectedText,split[0],textInfo+" Value is not correct");
    }

    public void selectRandomProduct(By key){
        List<WebElement> products = driver.findElements(key);
        int max=products.size();
        int min=1;
        int index= (int) Math.floor(Math.random() * (max-min + 1) + 1);
        WebElement element = products.get(index - 1);
        if (element.isDisplayed()) {getJSExecutor().executeScript("arguments[0].click();", element);
            logger.info(index + ".Ürün sayfasına gidildi");
        } else {
            logger.info(index + ".ürün nulunamadı!");
        }
    }
}

