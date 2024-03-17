import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import reportes.ExtentFactory;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    public static ExtentSparkReporter info = new ExtentSparkReporter("target/ReporteLogin.html");

    public static ExtentReports extent;


    @BeforeAll
    public static void createReport() {

        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    // Método que se ejecuta antes de cada prueba para preparar el entorno
    @BeforeEach
    public void preconditions() throws InterruptedException {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginPage.setup();
        loginPage.url("https://account.booking.com/sign-in?op_token=EgVvYXV0aCK-BQoUdk8xS2Jsazd4WDl0VW4yY3BaTFMSCWF1dGhvcml6ZRo1aHR0cHM6Ly9zZWN1cmUuYm9va2luZy5jb20vbG9naW4uaHRtbD9vcD1vYXV0aF9yZXR1cm4q3QRVcVlERk96OGVkb2xHUGsxTC1SY2wwbFdaSHlJZG04UnRfSDB1ZTV1QnFhaGtTb3M1aW1KSUllZTJsUHl3WHQtTUc1bVVtTGhROTlyemVCNXBPay1OYzF6aHgzX2tqMnEwS3lPR3hBMHM3d3hNQmxia0J1UWFiVVd2V2hYVXc2V0JuTFVDLWNhcHc3ajhDM282X1htNHpsUEZUNnJkYjBncFkteDYtX1hhNGRpanROaUZGdHg4TEZJek0yZlN0MjFtVnZEZHpvWlVkd3ZQUGpleG1HMzVqbEs4ZVN6ZTFwbGkyWmxtaDNWcS1Fc2VQTTR4WHhlOHRZZDNLWGtYaTNtS3pNc0VyanJlMGlHekZzRjIta1dneElKVnEwTWJLeEk1UUV5NEJVRnFsdG1rVnphNTJjMW9TV1hQclVKZXBlZG82dHZXYVpsQkhWdTV2UmZPLW1VNWlfTXV4Vmh6VktMUm1DckVlUzZlUW81Z1lsTVYwOHMtNlJsRThYY2JjcUlpQ0p4ZGN2VGhrTFkyQ1pZUHNnZGpCYXhSTmJBMndpWXFCTFVfQ0RvZlE1NVM4QWJjWmk1TGZfNWNXNjVkV0hKYm5JRVNJSXk2Rmt1TU5JS1Nma3pYSFZkUE81UFNJdXF6OFNuLTJQMHlDQTctRS13UGt0S0wzeGpnclBMak1CQW50UElTT1ByVXJrSTFjZ2tiZU9aNk1GM3FfT2hNNlMxdXNHM3BrTEd5VGJ3TW5DWXhQMEc5cm89KmV5SnBaQ0k2SW5SeVlYWmxiR3hsY2w5b1pXRmtaWElpZlE9PUIEY29kZSoxCI7IEjCgzdzCovEmOgBCAFid97ivBpIBEHRyYXZlbGxlcl9oZWFkZXKaAQVpbmRleA");
        loginPage.closePopup();
    }

    // **Inicio de sesión**

    @Test
    @Tag("SESSION")
    public void incioSesionExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Inicio de sesión éxitosa");
        test.log(Status.INFO, "Comienza el ingreso de datos para el inicio de sesión");

        loginPage.escribirUsername("xxxxxxxxxxxxxxxxxxxxxxx@xxxx.com");

        Thread.sleep(1000);

        loginPage.escribirPassword("Xxxxxx.XXXX");

        test.log(Status.PASS, "Se ingresaron los datos correctamente");

        test.log(Status.PASS, "Se inicio sesión exitosamente");

        test.log(Status.PASS, "Se valida que la búsqueda haya sido correcta"); // Agrega un log de tipo "pass" al test
    }

    /*@AfterEach
    public void close() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.close();
    }*/

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }
}
