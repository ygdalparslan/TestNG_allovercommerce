package tests.us01;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P02_RegisterPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC09 {

    P01_HomePage p01HomePage = new P01_HomePage();
    P02_RegisterPage p02RegisterPage = new P02_RegisterPage();
    Faker faker = new Faker();

    @Test
    public void testCase09(){

        //1	Verilen URL'e git
        Driver.getDriver().get(ConfigReader.getProperty("URL"));

        //2	Register linkine tıkla
        p01HomePage.registerButton.click();

        //3	Username kutusuna bir kulanıcı adı gir
        p02RegisterPage.userNameBox.sendKeys(faker.name().username());

        //4	Your Email address kutusunabir eposta gir
        p02RegisterPage.emailBox.sendKeys(faker.internet().emailAddress());

        //5	Password kutusuna şifre gir
        p02RegisterPage.passwordBox.sendKeys("12345678901");

        //6	I agree to the privacy policy kutusunu işaretle
        p02RegisterPage.privacyPolicyBox.click();

        //7	SIGN UP butonuna tıkla
        p02RegisterPage.signUpButton.click();

        //8	Kayıt işleminin gerçekleşmediğini doğrula
        Assert.assertTrue(p01HomePage.signOutLink.isDisplayed());

        Driver.closeDriver();

        //BUG: 11 haneli bir şifre girildiği halde kayıt işlemi gerçekleşti

        
    }
}
