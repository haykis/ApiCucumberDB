package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.PojoApi3_1;

import java.util.HashMap;
import java.util.Map;

public class Api3Test {

    @Given("Universite sorgulamalari yapilacak")
    public void universiteSorgulamalariYapilacak() {
        RestAssured.baseURI = "https://restcountries.com/v3.1";

        Map<String, Object> pathDeger = new HashMap<>();
        pathDeger.put("pad1", "name");
        pathDeger.put("pad2", "turkey");
        Response response = RestAssured.given().and().log().uri()
                .pathParams(pathDeger)
                .queryParam("capital", "Turkey")
                .when().get("/{pad1}/{pad2}")
                .then().assertThat().statusCode(200)
                .log().body().extract().response();
        Assert.assertTrue(response.body().asString().contains("Ankara"));
    }

    @And("ikinci sorudan devam edilir")
    public void ikinciSorudanDevamEdilir() {
        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        Response response = RestAssured.given().and().log().uri()
                .get("/booking/175").then().assertThat().statusCode(200).log().body().extract().response();

        Map<String, Object> resBody = response.body().as(Map.class);
       // Assert.assertEquals("Allen", resBody.get("lastname"));
        PojoApi3_1 pojoApi3_1=response.body().as(PojoApi3_1.class);
        System.out.println("pojoApi3_1.getBookingdates().getCheckin() = " + pojoApi3_1.getBookingdates().getCheckin());


    }

    @And("ucuncu senaryo odev test edilir")
    public void ucuncuSenaryoOdevTestEdilir() {
        RestAssured.baseURI = "https://www.boredapi.com";

        Response response = RestAssured.given().contentType(ContentType.ANY).accept(ContentType.ANY)
                .log().uri().pathParam("pad", "api")
                .queryParam("activity", "1")
                .when().get("/{pad}/").then().assertThat().statusCode(200)
                .log().body().extract().response();

        String body = response.body().asString();
        Assert.assertTrue(body.contains("Bored API"));

        Map<String, String> resBody = response.body().as(Map.class);
        Assert.assertEquals("Bored API",resBody.get("message"));



    }
}
