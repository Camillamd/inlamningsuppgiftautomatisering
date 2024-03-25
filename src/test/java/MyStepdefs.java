import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import static org.junit.Assert.assertEquals;
import java.util.Random;

public class MyStepdefs {

    private WebDriver driver;
    private String eMail;

    @Given("Jag registrerar medlemmens födelsenummer {int}\\/{int}\\/{int}")
    public void jagRegistrerarMedlemmensFodelsenummer(int day, int month, int year) {

        driver = new ChromeDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        WebElement rutaPersonnummer = driver.findElement(By.name("DateOfBirth"));
        String formatTillString = String.format("%02d/%02d/%02d", day, month, year);
        rutaPersonnummer.sendKeys(formatTillString);
    }

    @And("jag registrerar medlemmens namn {string}")
    public void jagRegistrerarMedlemmensNamn(String personNamn) {
        WebElement rutaNamn = driver.findElement(By.name("Forename"));
        rutaNamn.sendKeys(personNamn);
    }

    @And("jag registrerar medlemmens efternamn {string}")
    public void jagRegistrerarMedlemmensEfternamn(String efterNamn) {
        WebElement rutaEfterNamn = driver.findElement(By.name("Surname"));
        rutaEfterNamn.sendKeys(efterNamn);

    }


    @And("jag registrerar medlemmens e-mailadress")
    public void jagRegistrerarMedlemmensEMailadress() {

        Random slumpGenerator = new Random();
        eMail = "Iliana";
        eMail = eMail + slumpGenerator.nextInt(1000); // Lägg till en slumpad siffra (0-999)
        eMail = eMail + "@gladpask.com";

        WebElement rutaEmail = driver.findElement(By.name("EmailAddress"));
        rutaEmail.sendKeys(eMail);
    }

    @And("jag registrerar medlemmens e-mailadress igen")
    public void jagRegistrerarMedlemmensEMailadressIgen() {
        WebElement rutaEmailRepeat = driver.findElement(By.name("ConfirmEmailAddress"));
        rutaEmailRepeat.sendKeys(eMail);
    }

    @And("jag registrerar ett passord {string}")
    public void jagRegistrerarEttPassord(String losenOrd) {
        WebElement rutaPassOrd = driver.findElement(By.name("Password"));
        rutaPassOrd.sendKeys(losenOrd);
    }

    @And("jag registrerar passordet igen {string}")
    public void jagRegistrerarPassordetIgen(String losenOrdrepeat) {
        WebElement rutaPassOrdRepeat = driver.findElement(By.name("ConfirmPassword"));
        rutaPassOrdRepeat.sendKeys(losenOrdrepeat);
    }

    @And("jag kryssar i andra boxen vid vilken roll")
    public void jagKryssarIAndraBoxenVidVilkenRoll() {
        WebElement boxTva = driver.findElement(By.cssSelector("input[name='RolesWithinBasketball[1].Selected']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(boxTva).click().perform();

    }

    @And("jag kryssar i första rutan vid account confirmation")
    public void jagKryssarIForstaRutanVidAccountConfirmation() {
        WebElement boxett = driver.findElement(By.name("TermsAccept"));

        Actions actions = new Actions(driver);
        actions.moveToElement(boxett).click().perform();
    }

    @And("jag kryssar i andra rutan vid account confirmation")
    public void jagKryssarIAndraRutanVidAccountConfirmation() {
        WebElement boxtva = driver.findElement(By.name("AgeAccept"));
        //boxtva.click(); //funkar inte eftersom rutan inte blir aktiv

        Actions actions = new Actions(driver);
        actions.moveToElement(boxtva).click().perform();//metod för att röra musen till ett specifikt element på webbsidan
    }

    @And("jag kryssar i första boxen vid communication preferences")
    public void jagKryssarIForstaBoxenVidCommunicationPreferences() {
        WebElement rutaEtt = driver.findElement(By.name("OptInBECommunications"));

        Actions actions = new Actions(driver);
        actions.moveToElement(rutaEtt).click().perform();
    }

    @And("jag kryssar i andra rutan vid communication preferences")
    public void jagKryssarIAndraRutanVidCommunicationPreferences() {
        WebElement rutaTva = driver.findElement(By.name("OptInBEPartnerCommunications"));
    }

    @And("jag kryssar i rutan vid code of ethics and conduct")
    public void jagKryssarIRutanVidCodeOfEthicsAndConduct() {

        WebElement rutaSist = driver.findElement(By.name("AgreeToCodeOfEthicsAndConduct"));

        Actions actions = new Actions(driver);
        actions.moveToElement(rutaSist).click().perform();
    }

    @When("jag har registrerat namn och personnummer klickar jag på knappen Confirm and join")
    public void jagHarRegistreratNamnOchPersonnummerKlickarJagPaKnappenConfirmAndJoin() {

        WebElement klickRuta = driver.findElement(By.name("join"));
        klickRuta.click();

        driver.navigate().to("https://membership.basketballengland.co.uk/Account/SignUpConfirmation?beMembershipNumber=A103888&privilegeLevelId=2&memberTypeId=1");
    }

    @Then("är registreringen klar och jag kommer till min medlemssida")
    public void arRegistreringenKlarOchJagKommerTillMinMedlemssida() {

        WebElement field = driver.findElement(By.cssSelector("h2.bold.gray.text-center.margin-bottom-40"));
        String actual = field.getText();
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        assertEquals(expected, actual);

        System.out.println(actual);
    }


    @When("registreringen utan efternamn är klar klickar jag på knappen join")
    public void registreringenUtanEfternamnArKlarKlickarJagPaKnappenJoin() {

        WebElement felMeddelandeEfterNamnSaknas = driver.findElement(By.name("join"));
        felMeddelandeEfterNamnSaknas.click();

        try {
            Thread.sleep(2000); // Vänta i 2 sekunder för att det ska laddas
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Then("får jag upp ett felmeddelande där efternamn saknas")
    public void farJagUppEttFelmeddelandeDarEfternamnSaknas() {
        WebElement felmeddelandeRegUtanEfternam = driver.findElement(By.cssSelector("#member_lastname__label+ .field-validation-error span"));
        String actual = felmeddelandeRegUtanEfternam.getText();
        String expected = "Last Name is required";

        assertEquals(expected, actual);

        System.out.println(actual);
    }


    @And("jag registrerar ett slumpat felaktigt passord")
    public void jagRegistrerarEttSlumpatPassord() {
        Random slumpGenerator = new Random();
        String passOrd;
        passOrd = "aceg";
        passOrd = passOrd + slumpGenerator.nextInt(1000); // Lägg till en slumpad siffra (0-999)


        WebElement rutaFelLosen = driver.findElement(By.name("ConfirmPassword"));
        rutaFelLosen.sendKeys(passOrd);
    }


    @Then("får jag upp ett felmeddelande om fel lösenord")
    public void farJagUppEttFelmeddelandeOmFelLosenord() {
        WebElement felMeddelandeLabel = driver.findElement(By.cssSelector("#signup_form > div:nth-child(9) > div > div.row > div:nth-child(2) > div > span > span"));
        String actual = felMeddelandeLabel.getText();
        String expected = "Password did not match";
        assertEquals(actual, expected);
    }


    @When("jag har registrerart en ny medlem utan att bocka i etics and conducts klickar jag på knappen Confirm and join")
    public void jagHarRegistrerartEnNyMedlemUtanAttBockaIEticsAndConductsKlickarJagPaKnappenConfirmAndJoin() {
        WebElement klickRutaUtanIfylldEticsAndConduct = driver.findElement(By.name("join"));
        klickRutaUtanIfylldEticsAndConduct.click();
    }

    @Then("får jag ett felmeddelande att bocken inte är i kryssad vid etics and conduct")
    public void farJagEttFelmeddelandeAttBockenInteArIKryssadVidEticsAndConduct() {

        WebElement felmeddelandeLabelEtics = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div/div/form/div[11]/div/div[7]/span/span"));

        String actual = felmeddelandeLabelEtics.getText();
        String expected = "You must confirm that you have read, understood and agree to the Code of Ethics and Conduct";

        assertEquals(actual, expected);

        System.out.println(actual);
    }

}

