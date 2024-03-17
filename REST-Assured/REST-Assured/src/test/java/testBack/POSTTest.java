package testBack;


import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class POSTTest {

    @Test
    public void postTest01() {
        // Armo la solicitud requerida por la API
        JsonObject request = new JsonObject();
        request.addProperty("name", "space");
        request.addProperty("job", "leader");

        // Realizo una solicitud POST a la API con la solicitud construida anteriormente
        // y verifico que el código de respuesta sea 201 (creado)
        given()
                .contentType("application/json") //Tipo de archivo que subiremos
                .body(request) //Cuerpo de la respuesta
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201) // Respuesta de creación exitosa
                .log()
                .status() // Registro del estado de la solicitud
                .log()
                .body(); //Que devuelva el cuerpo de la respuesta
    }

    @Test
    public void postTest02() {
        // Creo los campos para el POST
        JsonObject request = new JsonObject();
        request.addProperty("name", "SpaceR");
        request.addProperty("job", "leader");

        // Realizo una solicitud POST con la nueva solicitud y verifico que el
        // código de respuesta sea 201, además, verifico que el nombre retornado
        // sea igual al nombre enviado en la solicitud.
        given()
                .contentType("application/json")
                .body(request)
                .post("https://reqres.in/api/users")
                .then()
                .log().status() // Registro del estado de la solicitud
                .statusCode(201) // Verificación del código de respuesta
                .log().body() // Registro del cuerpo de la respuesta
                //Validaciones del body
                .body("name", equalTo("SpaceR")) // Verificación del nombre en la respuesta
                .body("job", equalTo("leader")) // Verifico que el trabajo en la respuesta sea "leader"
                .body("createdAt", containsString("2024-03-17")); // Verifico que la fecha de creación contenga "2024-03-17"
    }

    @Test
    public void postTestFallido() {
        // Construyo una solicitud con un correo electrónico pero sin contraseña
        JsonObject request = new JsonObject();
        request.addProperty("email", "space@space");

        // Realizo una solicitud POST a la API para intentar iniciar sesión
        Response response =
                given()
                        .contentType("application/json")
                        .body(request).post("https://reqres.in/api/login");

        // Extraigo el mensaje de error de la respuesta
        String error = response.jsonPath().getString("error");

        // Verifico que el código de estado de la respuesta sea 400 (Solicitud incorrecta)
        Assert.assertEquals(400, response.getStatusCode());

        // Verifico que el mensaje de error sea "Missing password"
        Assert.assertEquals(error, "Missing password");

        // Imprimo el cuerpo de la respuesta y el mensaje de error
        System.out.println("Body del response: " + response.getBody().asString());
        System.out.println("EL MENSAJE DE ERROR ES: " + error);

        // Imprimo el código de estado y el tiempo de respuesta
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Tiempo de ejecución: " + response.time() + " milisegundos");
    }
}
