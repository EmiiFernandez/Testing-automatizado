package testBack;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PUTTest {

    @Test
    public void putTest01() {
        //Cuando el método necesita un request creamos JsonObjetc
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", "SPACE");
        requestBody.addProperty("job", "CAMBIADO POR RESTASSURE");

        given()
                .contentType("application/json")
                .body(requestBody)
                .put("https://reqres.in/api/users/50")
                //Después del put comenzamos la validación
                .then()
                .statusCode(200)
                .log().status()
                .log()
                .body();
    }
}
