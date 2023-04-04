package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.MainPageHA;
import utilities.ConfigReader;
import utilities.Driver;

public class US001 {
    MainPageHA mainPage=new MainPageHA();
    Faker faker = new Faker();
    String ssnNo;
    String firstName;
    String lastName;

    @Given("go to  and register page")
    public void goToAndRegisterPage() {
        Driver.getDriver().get(ConfigReader.getProperty("mainPage"));
        mainPage.accountMenu.click();
        mainPage.register.click();
    }

    @When("enter ssn nummer")
    public void enterSsnNummer() {
        ssnNo=faker.idNumber().ssnValid();
        Driver.waitBasic(1);
        mainPage.ssn.sendKeys(ssnNo);
    }

    @Then("check ssn valid and nine rigid")
    public void checkSsnValidAndNineRigid() {
        Assert.assertTrue(ssnNo.length()==11);
        Assert.assertEquals("-",ssnNo.charAt(3),ssnNo.charAt(6));
    }

    @When("enter first name")
    public void enterFirstName() {
        Driver.waitBasic(1);
        firstName=faker.name().firstName();
        mainPage.firstName.sendKeys(firstName);
        Driver.waitBasic(1);
    }

    @Then("check first name space and value")
    public void checkFirstNameSpaceAndValue() {
        WebElement value=Driver.getDriver().findElement(By.xpath("//input[@name='firstname' and @value='"+firstName+"']"));
        Assert.assertTrue(value.isDisplayed());
        Assert.assertTrue(value.getAttribute("value").matches("[a-zA-Z]+"));
        Assert.assertFalse(value.getAttribute("value").matches(".*[0-9].*"));
        Assert.assertFalse(value.getAttribute("value").contains(" "));
    }

    @When("enter last name")
    public void enterLastName() {
        Driver.waitBasic(1);
        lastName=faker.name().lastName();
        mainPage.lastName.sendKeys(lastName);
    }

    @Then("Check it is not empty, contains chars and not contains nummer")
    public void checkItIsNotEmptyContainsCharsAndNotContainsNummer() {
        WebElement value=Driver.getDriver().findElement(By.xpath("//input[@name='lastname' and @value='"+lastName+"']"));
        Assert.assertTrue(value.isDisplayed());
        System.out.println("value.getAttribute(\"value\") = " + value.getAttribute("value"));
        Assert.assertTrue(value.getAttribute("value").matches("[a-zA-Z]+"));
        Assert.assertFalse(value.getAttribute("value").matches(".*[0-9].*"));
        Assert.assertFalse(value.getAttribute("value").contains(" "));
    }
}
