package stepdefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class ApiDers2 {

    @Given("Path ve Match metotlari ve classlari")
    public void pathVeMatchMetotlariVeClasslari() {
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/175")
                .then().assertThat().statusCode(Matchers.instanceOf(Integer.class))
                .and().assertThat().statusCode(Matchers.greaterThan(199))
                .and().assertThat().statusCode(Matchers.lessThan(300))
                .and().assertThat().statusCode(Matchers.anyOf(Matchers.equalTo(200), Matchers.equalTo(201)))
                .and().assertThat().header("Server", "Cowboy")
                .and().assertThat().body(Matchers.contains("firstname"))
                .extract().response();

        System.out.println("response.path(\"firstname\") = " + response.path("firstname"));
    }
}
