package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseTest {

    protected static WebDriver driver;
    protected final Logger logger = LogManager.getLogger(BaseTest.class);
    //protected static Browser browser = new Browser();

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        BaseTest.driver = driver;
    }

    @Before
    public void setUp() {

        logger.info("SetUp işlemi yapılıyor...");
        System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
        driver=new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.beymen.com/");
    }

    @After
    public void shutDown() {

        if (getDriver() != null) {
            logger.info("Driver kapatılıyor...");
            getDriver().quit();
        }
    }

}
