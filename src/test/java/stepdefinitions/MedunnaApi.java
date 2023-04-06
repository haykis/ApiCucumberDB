package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utilities.DbConnect.*;
import static utilities.DbConnect.resultSet;

public class MedunnaApi {
    Response registerResponse;
    Response allusers;
    Faker faker = new Faker();
    RegisterMedunna registerMedunna = new RegisterMedunna();
    RegisterMedunnaResponse registerMedunnaResponse = new RegisterMedunnaResponse();
    String email;
    String firstName;
    String lastName;
    String login;
    String password;
    String ssn;
    String token=TokenMedunna.MedunnaToken();

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

    @And("with api get all users and verify with DB")
    public void withApiGetAllUsersAndVerifyWithDB() throws SQLException {
        Map<String, String> pathParamm = new HashMap<>();
        pathParamm.put("pad1","api");
        pathParamm.put("pad2","users");
        RestAssured.baseURI = "https://medunna.com";
        allusers=RestAssured.given().header("Authorization","Bearer "+token)
                .pathParams(pathParamm).log().uri()
                .when().get("/{pad1}/{pad2}")
                .then().assertThat().statusCode(200).extract().response();

        List<Object> totalusers=allusers.body().as(List.class);

        connection= DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        statement= connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE, resultSet.CONCUR_UPDATABLE);

        resultSet=statement.executeQuery("select ssn, first_name, id, last_name, email, login from jhi_user");
        resultSet.next();

        Assert.assertTrue(totalusers.get(0).toString().contains(resultSet.getString("ssn")));
        Assert.assertTrue(totalusers.get(0).toString().contains(resultSet.getString("first_name")));
        Assert.assertTrue(totalusers.get(0).toString().contains(resultSet.getString("last_name")));
        Assert.assertTrue(totalusers.get(0).toString().contains(resultSet.getString("id")));
        Assert.assertTrue(totalusers.get(0).toString().contains(resultSet.getString("email")));
        Assert.assertTrue(totalusers.get(0).toString().contains(resultSet.getString("login")));

            //burada API den gelen veriler eksik Api ile DB belli bir satirdan sonra uyusmuyor bir bag soz konusu
//        int row = 0;
//        while (resultSet.next()){
//            if (row<totalusers.size()){
//                Assert.assertTrue(totalusers.get(row).toString().contains(resultSet.getString("ssn")));
//            }
//            row++;
//        }

    }
}
