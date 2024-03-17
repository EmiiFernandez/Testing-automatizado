package testBack;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

//API utilizada: https://reqres.in/
public class GETTest {

    @Test
    public void getTest01() {
        // Realiza una solicitud GET a la API de ReqRes para obtener usuarios de la página 2
        Response resGet = RestAssured.get("https://reqres.in/api/users?page=2");
        // Imprime el cuerpo de la respuesta
        //System.out.println(resGet.getBody().asString());
        System.out.println(resGet.getBody().asPrettyString());
        // Imprime el código de estado de la respuesta para indicar el éxito o error
        System.out.println("Status: " + resGet.statusCode());
        // Imprime la fecha y hora de la respuesta proporcionada por el servidor
        System.out.println("Date: " + resGet.getHeader("Date"));
        // Imprime el tiempo de ejecución de la solicitud en milisegundos
        System.out.println("Tiempo de ejecución: " + resGet.getTime());
    }

    @Test
    public void getTest02() {
        // Enviar una solicitud GET a la API:
        Response resGet = RestAssured.get("https://reqres.in/api/users?page=2");

        // Extraer información de la respuesta:
        int statusCode = resGet.statusCode();
        JsonPath body = resGet.jsonPath();
        String name1 = body.getString("data.first_name[0]");
        String email1 = body.getString("data.email[0]");
        String name3 = body.getString("data.first_name[2]");
        String email3 = body.getString("data.email[2]");

        // Realizar aserciones para verificar la respuesta:
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(name1, "Michael");
        Assert.assertEquals(email1, "michael.lawson@reqres.in");
        Assert.assertEquals(name3, "Tobias");
        Assert.assertEquals(email3, "tobias.funke@reqres.in");

        // Imprimir información en la consola:
        System.out.println("Código de status: " + statusCode);
        System.out.println("Datos primer registro: Nombre: " + name1 + " - " + "Email: " + email1);
        System.out.println("Datos tercer registro: Nombre: " + name3 + " - " + "Email: " + email3);
    }

    @Test
    public void getTest03() {
        given()
                // Establecer la solicitud (request).
                .get("https://reqres.in/api/users?page=2")
                // Afirmar las expectativas (assertions)
                .then()
                // Verificar que el código de estado sea 200
                .statusCode(200)
                // Deshabilitar la impresión del cuerpo de la respuesta
                // Si body esta en true (vacío) muestra en formato json
                .log().body(false);
    }
}
