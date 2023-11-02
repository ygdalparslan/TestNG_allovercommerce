package tests.us06;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P06_ShoppingPage;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class TC010 {

    private final String testName = "US06 || TC01-Kullanıcı siteden alışveris yapabilmeli";
    private final String description = "Tüm alanlara valid veri girilmeli";
    private final String raporMesaji = "Thank you. Your order has been received. Yazısı görülüp Alışveriş tamamlanmalı";

    @Test
    public void testCase01(){

        P01_HomePage p01HomePage =new P01_HomePage();
        P06_ShoppingPage p06ShoppingPage =new P06_ShoppingPage();
        Actions actions =new Actions(Driver.driver);

        //1	Web sitesine gidilir.
        //2	Log in olunur.
        ReusableMethods.performLogin();

        //3	Search box  kısmına aranacak ürün ismi yazılır ve aratılır.
        String aranacakUrun ="pen";
        ReusableMethods.wait(7);
        p01HomePage.searchBox.sendKeys("pen", Keys.ENTER);
        ReusableMethods.wait(5);

        //4 Bulunan sonuçlardan istenen ürün üzerine gelinerek sepet simgesine (Cart) tıklanır.
        List<WebElement> urunListesi = Driver.getDriver().findElements(By.xpath("//li[@class='product-wrap']"));

        if (urunListesi.size() >= 2) {
            WebElement ikinciUrun = urunListesi.get(1);
            actions.moveToElement(ikinciUrun).perform();
        } else {
            System.out.println("Listede en az iki ürün bulunmalıdır.");
        }

        p06ShoppingPage.addToCartButton.click();

        //5	Site sayfasından sağ üst sekmede yer alan sepet simgesine (Cart) tıklanır.
        p06ShoppingPage.cartButton.click();

        //6 Açılan sekmeden "View Cart" butonuna tıklanır
        p06ShoppingPage.viewCartButton.click();

        //7	Sepetteki ürün "Quantity" kısmının altında yer alan alandan arttırıp veya azaltılmalı
        p06ShoppingPage.cartButtonDetailQuantityPlus.click();

        //8	Fatura adresi "Cart Totals" kısmının altında yer alan"Shipping" kısmında gözükmeli
        p06ShoppingPage.shippingText.isDisplayed();

        //9	Proceed To Checkout Button tıklanır
        p06ShoppingPage.proceedToCheckoutButton.sendKeys(Keys.ENTER);

        //10 Ekranın sağ tarafında "Payment Methods" kısmının altında yer alan ödeme seçenekleri seçilmeli
        p06ShoppingPage.paymentMethodsWireTransfer.sendKeys(Keys.ENTER);

        //11	"Place Order" butonu seçilmeli alışveriş tamamlanmalı
        p06ShoppingPage.placeOrderButton.sendKeys(Keys.ENTER);

        //12 "Thank you. Your order has been received." doğrulanır
        String xpectedData ="Thank you. Your order has been received.";
        ReusableMethods.verifyData(p06ShoppingPage.verfyOrderReceived,xpectedData);

        Driver.closeDriver();

    }
}