package stepdefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;

public class ApiTest {

    @Given("Api ile testler yapilir")
    public void apiIleTestlerYapilir() {
        RestAssured.baseURI="http://universities.hipolabs.com";

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .and().accept(ContentType.JSON)
                .and().log().method()
                .and().log().uri()
                .and().queryParams("country", "Turkey")
                .and().pathParam("pad", "search")
                .when().get("/{pad}")
                .then().assertThat().statusCode(200)
                //.log().body()
                .extract().response();

        RestAssured.given().contentType(ContentType.JSON)
                .and().accept(ContentType.ANY)
                .and().queryParams("country", "Turkey")
                .and().pathParam("pad", "/search")
                .when().get("{pad}")
                .then().assertThat().statusCode(200);

            List<String> uni=response.body().as(List.class);
        System.out.println("uni.size() = " + uni.size());



    }
}
