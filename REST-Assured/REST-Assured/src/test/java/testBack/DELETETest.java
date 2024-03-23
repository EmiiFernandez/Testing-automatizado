package testBack;


import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DELETETest {
    // Este método realiza una prueba de eliminación de un usuario utilizando el método DELETE en la API Rest reqres.in
    @Test
    public void DeleteTest01() {
        // Envía una solicitud DELETE para eliminar el usuario con ID 2 y guarda la respuesta
        Response responseBody = given().delete("https://reqres.in/api/users/2");

        // Verifica que el código de estado de la respuesta sea 204
        Assert.assertEquals(responseBody.statusCode(), 204);

        // Imprime el código de respuesta obtenido
        System.out.println("El código de respuesta es: " + responseBody.statusCode());

        // Imprime el tiempo que tardó el servicio en responder en milisegundos
        System.out.println("El servicio se tardó " + responseBody.getTime() + " milisegundos en responder");
    }

    @Test
    public void deleteTest02() {
        String urlReqres = "https://reqres.in/api/";
        String pathUser = "users/";
        String deleteUser = "55";

        Response responseBody = given().delete(urlReqres + pathUser + deleteUser);
        Assert.assertEquals(responseBody.statusCode(), 204);

        System.out.println("El código de respuesta es: " + responseBody.statusCode());
        if (responseBody.statusCode() == 204) {
            System.out.println("Se elimino el usuario: " + deleteUser);
        }
        System.out.println("El servicio se tardo " + responseBody.getTime() + " milisengundos en responder");



    }
}
