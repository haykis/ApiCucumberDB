package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MainPageHA {

    public MainPageHA(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//li[@id='account-menu']")
    public WebElement accountMenu;

    @FindBy(xpath = "//*[@id='login-item']")
    public WebElement SignIn;

    @FindBy(xpath = "(//a[@role='menuitem'])[2]")
    public WebElement register;

    @FindBy(xpath = "//*[@id='ssn']")
    public WebElement ssn;

    @FindBy(xpath = "//input[@id='firstname']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@id='lastname']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@id='address']")
    public WebElement adres;

    @FindBy(xpath = "//input[@id='mobilephone']")
    public WebElement mobilphone;

    @FindBy(xpath = "//input[@id='username']")
    public WebElement userName;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement email;

    @FindBy(xpath = "//input[@id='firstPassword']")
    public WebElement firstPassword;

    @FindBy(xpath = "//input[@id='secondPassword']")
    public WebElement secondPassword;








}
