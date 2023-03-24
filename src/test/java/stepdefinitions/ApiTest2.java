package stepdefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import pojo.PojoApi1;

import java.util.Map;

public class ApiTest2 {

    @Given("base url de calisir")
    public void baseUrlDeCalisir() {
        RestAssured.baseURI="http://www.boredapi.com/api/activity";

        RestAssured.given().get().body().prettyPrint();

        Map<String, Object> result = RestAssured.given().get().body().as(Map.class);

        System.out.println("result.get(\"activity\") = " + result.get("activity"));

        System.out.println("***********");

        PojoApi1 pojoApi1 = RestAssured.get().body().as(PojoApi1.class);
        System.out.println("pojoApi1.getActivity() = " + pojoApi1.getActivity());

    }
}
