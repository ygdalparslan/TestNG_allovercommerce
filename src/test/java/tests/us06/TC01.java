package tests.us06;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P06_ShoppingPage;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class TC01 {

    private final String testName = "US06 || TC01-Kullanıcı search box tan istediği ürünü arayabilmeli";
    private final String description = "search box'a mağazada bulunan bir ürün girilmeli";
    private final String raporMesaji = "Aranan ürün sonuçlarda görülmeli";

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
        p01HomePage.searchBox.sendKeys(aranacakUrun, Keys.ENTER);
        ReusableMethods.wait(5);

        //4 Bulunan sonuçlarda aranan ürünün olduğu doğrulanır.

        List<WebElement> urunListesi = Driver.getDriver().findElements(By.xpath("//li[@class='product-wrap']"));
        boolean expectedData=true;

        if (!urunListesi.isEmpty()) {
            for (WebElement w : urunListesi) {

                if (w.getText().contains(aranacakUrun)) {
                    expectedData=true;
                    break;
                }else {
                    expectedData=false;
                }
            }
        }
        Assert.assertTrue(expectedData);

        Driver.closeDriver();

    }
}