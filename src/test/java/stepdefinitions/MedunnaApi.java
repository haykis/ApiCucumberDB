package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.RegisterMedunna;
import pojo.RegisterMedunnaResponse;
import utilities.TokenMedunna;

import java.sql.DriverManager;
import java.sql.SQLException;

import static utilities.DbConnect.*;
import static utilities.DbConnect.resultSet;

public class MedunnaApi {
    Response registerResponse;
    Faker faker = new Faker();
    RegisterMedunna registerMedunna = new RegisterMedunna();
    RegisterMedunnaResponse registerMedunnaResponse = new RegisterMedunnaResponse();
    String email;
    String firstName;
    String lastName;
    String login;
    String password;
    String ssn;

    @Given("make a new register with Api")
    public void makeANewRegisterWithApi() {
         email = faker.internet().emailAddress();
         firstName= faker.name().firstName();
         lastName = faker.name().lastName();
         login = firstName+"_"+lastName;
         password = faker.internet().password(8,12,true,true,true);
         ssn = faker.idNumber().ssnValid();

        registerMedunna.setEmail(email);
        registerMedunna.setFirstName(firstName);
        registerMedunna.setLastName(lastName);
        registerMedunna.setLogin(login);
        registerMedunna.setPassword(password);
        registerMedunna.setSsn(ssn);

        String token=TokenMedunna.MedunnaToken();
        System.out.println("token = " + token);

         registerResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .and().accept(ContentType.ANY)
                .header("Authorization","Bearer "+token)
                .body(registerMedunna).when()
                .post("https://medunna.com/api/register");
    }

    @When("verify my registration with Api")
    public void verifyMyRegistrationWithApi() {
        registerMedunnaResponse =registerResponse.as(RegisterMedunnaResponse.class);
        Assert.assertEquals(registerMedunna.getFirstName(),registerMedunnaResponse.getFirstName());
        Assert.assertEquals(registerMedunna.getLastName(),registerMedunnaResponse.getLastName());
        Assert.assertEquals(registerMedunna.getSsn(),registerMedunnaResponse.getSsn());
        Assert.assertEquals(registerMedunna.getEmail(),registerMedunnaResponse.getEmail());
        Assert.assertEquals(registerMedunna.getLogin().toLowerCase(),registerMedunnaResponse.getLogin());

        System.out.println("registerMedunna.toString() = " + registerMedunna.toString());
        System.out.println("registerMedunnaResponse.toString() = " + registerMedunnaResponse.toString());
    }

    @Then("verify my registration with DB")
    public void verifyMyRegistrationWithDB() throws SQLException {
        connection= DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        statement= connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE, resultSet.CONCUR_UPDATABLE);
        //DB baglantisi kuruldu
        resultSet=statement.executeQuery("select ssn from jhi_user");
        int row = 0;
        while (resultSet.next()){
            if(resultSet.getString("ssn").equals(ssn)){
                System.out.println("resultSet.getString(\"ssn\") = " + resultSet.getString("ssn"));
                System.out.println("Yeni kayit "+row+" satirinda bulundu");
            }
            row++;
        }
        System.out.println("row = " + row);
    }
}
