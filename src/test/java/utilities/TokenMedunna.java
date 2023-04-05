package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TokenMedunna {

    public static String MedunnaToken() {

        String url = "https://medunna.com/api/authenticate";

        Map<String, String> expectedData = new HashMap();
        expectedData.put("username", "batch_yuzuc");
        expectedData.put("password", "Batch.103");
        Response response = given().contentType(ContentType.JSON).body(expectedData).when().post(url);

        return response.path("id_token");
    }
}
