package testBack;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
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
                //muestra el status code
                .log().status()
                //muestra el body
                .log().body();
    }

    @Test
    public void putTest02() {
        // Creo un objeto JSON para representar el cuerpo de la solicitud PUT
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", "SPACE");
        requestBody.addProperty("job", "CAMBIADO POR RESTASSURE");

        // Realizo una solicitud PUT a la API para modificar un recurso específico
        Response responseBody =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .put("https://reqres.in/api/users/55");

        // Extraigo los valores modificados del cuerpo de la respuesta
        String nombreModificado = responseBody.jsonPath().getString("name");
        String trabajoModificado = responseBody.jsonPath().getString("job");

        // Verifico que los valores modificados sean los esperados
        Assert.assertEquals(nombreModificado, "SPACE");
        Assert.assertEquals(trabajoModificado, "CAMBIADO POR RESTASSURE");

        // Verifico que el código de estado de la respuesta sea 200 (Éxito)
        Assert.assertEquals(responseBody.statusCode(), 200);

        // Verifico que la fecha de actualización contenga "2024-03-17"
        given()
                .contentType("application/json")
                .body(requestBody)
                .put("https://reqres.in/api/users/55")
                .then()
                .body("updatedAt", containsString("2024-03-17"));

        // Imprimo los valores modificados, el código de estado y el tiempo de respuesta
        System.out.println("El nombre modificado es: " + nombreModificado);
        System.out.println("El trabajo modificado es: " + trabajoModificado);
        System.out.println("El código de respuesta es: " + responseBody.statusCode());
        System.out.println("El servicio tardó: " + responseBody.getTime() + " milisegundos");
    }
}
