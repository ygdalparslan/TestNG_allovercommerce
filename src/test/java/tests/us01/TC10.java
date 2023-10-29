package tests.us01;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P02_RegisterPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC10 {

    P01_HomePage p01HomePage = new P01_HomePage();
    P02_RegisterPage p02RegisterPage = new P02_RegisterPage();
    Faker faker = new Faker();

    @Test
    public void testCase10(){

        //1	Verilen URL'e git
        Driver.getDriver().get(ConfigReader.getProperty("URL"));

        //2	Register linkine tıkla
        p01HomePage.registerButton.click();

        //3	Username kutusuna bir kulanıcı adı gir
        p02RegisterPage.userNameBox.sendKeys(faker.name().username());

        //4	Your Email address kutusunabir eposta gir
        p02RegisterPage.emailBox.sendKeys(faker.internet().emailAddress());

        //5	Password kutusuna şifre gir
        p02RegisterPage.passwordBox.sendKeys("1234567890123");

        //6	I agree to the privacy policy kutusunu işaretle
        p02RegisterPage.privacyPolicyBox.click();

        //7	SIGN UP butonuna tıkla
        p02RegisterPage.signUpButton.click();

        //8	Kayıt işleminin gerçekleştiğini doğrula
        p01HomePage.signOutLink.isDisplayed();
        
    }
}