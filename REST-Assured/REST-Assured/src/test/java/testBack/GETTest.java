package testBack;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class GETTest {

    @Test
    public void getTest01() {
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

}
