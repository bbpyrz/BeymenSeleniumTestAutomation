package test;


import driver.BaseTest;
import org.junit.Before;
import org.junit.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

public class MainTest extends BaseTest {

    ProductPage productPage;
    HomePage homePage;
    CartPage cartPage;

    @Before
    public void before() {
        productPage = new ProductPage(getDriver());
        homePage = new HomePage(getDriver());
        cartPage=new CartPage(getDriver());
    }

    @Test
    public void mainTest(){
        homePage.home();
        homePage.acceptCookies();
        homePage.closeGenderSectionPopUp();
        homePage.sendKeysToSearchBox(1);
        homePage.clearSearchBox();
        homePage.sendKeysToSearchBox(2);
        homePage.clickEnterFromKeyboard();
        homePage.clickARandomProduct();
        productPage.takeProductPriceAndInfo();
        productPage.addToCart();
        productPage.goToCart();
        cartPage.checkPriceOfProduct(productPage.getPrice());
        cartPage.checkNumberOfProduct();
        cartPage.deleteProduct();
        cartPage.checkEmptyCart();

    }
}
