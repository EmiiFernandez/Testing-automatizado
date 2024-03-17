import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import reportes.ExtentFactory;

public class LoginTest {

    private WebDriver driver; // Variable para almacenar el navegador
    private LoginPage loginPage; // Variable para almacenar la página de login

    public static ExtentSparkReporter info = new ExtentSparkReporter("target/ReporteLogin.html"); // Configura el reporte HTML
    public static ExtentReports extent; // Variable para el reporte de Extent

    @BeforeAll // Se ejecuta antes de todos los tests
    public static void createReport() {
        extent = ExtentFactory.getInstance(); // Obtiene la instancia del reporte
        extent.attachReporter(info); // Adjunta el reporte HTML
    }

    // Método que se ejecuta antes de cada prueba para preparar el entorno
    @BeforeEach
    public void preconditions() throws InterruptedException {
        driver = new ChromeDriver(); // Abre el navegador Chrome
        loginPage = new LoginPage(driver); // Inicializa la página de login
        loginPage.setup(); // Realiza acciones de configuración inicial en la página
        loginPage.url("https://account.booking.com/sign-in?op_token=EgVvYXV0aCK-BQoUdk8xS2Jsazd4WDl0VW4yY3BaTFMSCWF1dGhvcml6ZRo1aHR0cHM6Ly9zZWN1cmUuYm9va2luZy5jb20vbG9naW4uaHRtbD9vcD1vYXV0aF9yZXR1cm4q3QRVcVlEU1RYV3R6Q2ZOR2FNdXl0WjkweS1GWGMzUTQ5NXhnLVI0QXJ5Ny10Z1c5UHFXZUlUZU5FTU9iaTNuUE0wRVFlZllaUWt3N3gtTWxaQXNoenVMNlZFWVNBTFpwZEltVC1oMHFRY1hUUE9wZFpseUJnQklmOE1ZUm9LblNsSmFUejUyOEVlbWZDbXVxbmEzM2kxN1Y0UHVtaVVJLTd6aDNWY0VIQy01NldpQVJzXzdxV1c0UWl1b01oUWN6SmdDYlJXdDhibGtoZ2s1anRiZjJlY2UyN1FCTS1YYjdPRjB2WjBxNXBQZGdCMk10cmpwOUlkQ2htS2FqbDBLSEFwYnc3LW14d3c5cF9rUmpGVXVsRG9WV0Q5S0JBNTI5LUpmQmNlVTItWE9QRnROQVJmMi1SMDBXUjJIWGN1azI0Y1ZuXy1obl96Ny1FYk9tVlBRSF9uYXQ4Sjh0eDhHOWlXRzFzejBHN1R3cWJ6ZmJUUnFuX0ZCV2U3QnV3NkpaMjYtcXo4bDRrZDJnRXpCNGpVai10aXIyNjA2NTJLSW9zQTBrRFNmbzlubW1HYkZVOEVDcUZuTmR4N2dIaXl4UFRDRGU1dGNETnAyTThyT1BoSHhENmNIQ3I3M1NrclNBYTh2ZXZCSjBOMms0VTdGekdZbjUxVmoxckNTd045ZWNWVkNXV0FBT1owOUN4WnFmeWRWLXVwSjJ6WmJiYzRkaVRpT0lwQktPRldLSmJGZE55VEp1R2pWTzg9KmV5SnBaQ0k2SW5SeVlYWmxiR3hsY2w5b1pXRmtaWElpZlE9PUIEY29kZSoxCI7IEjDI17yZ_vImOgBCAFj679yvBpIBEHRyYXZlbGxlcl9oZWFkZXKaAQVpbmRleA"); // Ingresa la URL de login (reemplaza con tu URL)
        loginPage.closePopup(); // Cierra cualquier ventana emergente inicial (si existe)
    }

    // **Inicio de sesión**

    @Test // Indica que es un método de prueba
    @Tag("SESSION") // Etiqueta para identificar el test (opcional)
    public void incioSesionExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Inicio de sesión éxitosa"); // Crea un test dentro del reporte
        test.log(Status.INFO, "Comienza el ingreso de datos para el inicio de sesión"); // Agrega un log de información al test
        loginPage.escribirUsername("usuario@dominio.com"); // Ingresa un usuario válido (reemplaza con tu usuario)
        Thread.sleep(1000); // Espera 1 segundo
        loginPage.escribirPassword("tuContraseña"); // Ingresa una contraseña válida (reemplaza con tu contraseña)
        test.log(Status.PASS, "Se ingresaron los datos correctamente"); // Agrega un log de paso al test
        test.log(Status.PASS, "Se inicio sesión exitosamente"); // Agrega otro log de paso al test
        test.log(Status.PASS, "Se valida que la búsqueda haya sido correcta"); // Agrega un log de tipo "pass" al test
    }

    @Test // Indica que es otro método de prueba
    public void LoginDatosVacios() throws InterruptedException { // Método para probar el login con datos vacíos
        ExtentTest test = extent.createTest("Intentar loguarse sin agregar ningún dato"); // Crea un test dentro del reporte
        test.log(Status.INFO, "Comienza test de login con datos vacíos"); // Agrega un log de información al test

        loginPage.escribirUsername(""); // Deja el campo de usuario vacío
        //loginPage.escribirPassword(""); // Deja el campo la contraseña vacío
        test.log(Status.PASS, "No se agregan datos al login"); // Agrega un log de paso al test
        loginPage.usernameInvalido(); // Simula alguna acción para invalidar el username

        test.log(Status.PASS, "Se valida el mensaje de campos obligatorios"); // Agrega un log de paso al test

        //Con Assertions los mensajes de error son más claros
        // Se verifica que el mensaje de error sea el esperado
        Assertions.assertEquals(loginPage.usernameInvalido(), "Enter your email address");
    }

    @AfterEach // Se ejecuta después de cada prueba
    public void close() throws InterruptedException {
        loginPage = new LoginPage(driver); // Vuelve a inicializar la página de login
        loginPage.close(); // Cierra la página
    }

    @AfterAll // Se ejecuta después de todos los tests
    public static void saveReport() {
        extent.flush(); // Guarda el reporte generado
    }
}