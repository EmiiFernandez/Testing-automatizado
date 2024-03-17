import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import reportes.ExtentFactory;

import java.io.IOException;

public class RegisterTest {

    WebDriver driver;

    RegisterPage registerPage;

    public static ExtentSparkReporter info = new ExtentSparkReporter("target/ReporteRegistro.html"); // Configura el reporte HTML
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
        registerPage = new RegisterPage(driver); // Inicializa la página de login
        registerPage.setup(); // Realiza acciones de configuración inicial en la página
        registerPage.url("https://account.booking.com/sign-in?op_token=EgVvYXV0aCKuBQoUdk8xS2Jsazd4WDl0VW4yY3BaTFMSCWF1dGhvcml6ZRo1aHR0cHM6Ly9zZWN1cmUuYm9va2luZy5jb20vbG9naW4uaHRtbD9vcD1vYXV0aF9yZXR1cm4qzQRVcG9EU1RYV3R6Q2ZOR2JtQmRiMkotdTZLdW16VXBXSHV3YWxFYkdXR2RDUGxIZUZISzVLSGNUNWZ5RjBYMjNlWE5MOVRaRWJYZDRjU2xFTktpd3h1TmhzZm5GSXdPNjNSUk1VUXkxeTJnWkRKbUNVZVVLUThJYUl2TTZldWhUcDV6NVFjcERGUnJTekRrUGRiRHo0NWFyYWFSM2lZTVpGdThCeG9IY3VQR2tQeEpsREJrZUJTMFNSUDdmQnpCaG8weVQ3ajByaERtR1JLdnh5TGN1S0ctVGdmMnNid0RmcWdZTzZEZ1hTNDBfbHNOUmxwdGtRVkRPTXpJU0pEaC16aldNcWdNVkRnNEpGc1phMFRxMC1OLTBGZi00Snh4OGhNRUVjVkU0OVhQd3N1N0RrT09vMjY3bGN5Q001NUZ4YjEzVGlvRjhGRWJjNC1MRU4zRWt5ZGFtMFlNQ2tSa2h6ZTZTbExSNGh2akFnSThNTUQtRUJjU2RZOHpkOUlKM0x6VURoMUJHdTFMMnZlRXpjNXp1MFk0T3FYdTNKZkVDZWtLWFNISFRwUFFhOGphTU1PWkhwZWNUN00yMC15TUU5eUdLVjRZbXRRaUNTQjg2anViY3JfdnFBV3V0ZFQtb001ZzQwWDVGNEpvcm50NnVKN25SY1E4RVFybmE5S2JKcU5XTTJ1ckN4QlRJQ0MxRFZvS0dDUkM0amxud0pNZF9pT3QxSjRTWT0qZXlKcFpDSTZJblJ5WVhabGJHeGxjbDlvWldGa1pYSWlmUT09QgRjb2RlKjEIjsgSMP_HgoeL8yY6AEIAWO793K8GkgEQdHJhdmVsbGVyX2hlYWRlcpoBBWluZGV4"); // Ingresa la URL de register)
        registerPage.closePopup(); // Cierra cualquier ventana emergente inicial (si existe)
    }

    @Test
    public void registroExitoso() throws InterruptedException, IOException {
        ExtentTest test = extent.createTest("Test de registro de usuario exitoso"); // Crea un test dentro del reporte
        test.log(Status.INFO, "Comienza el ingreso de datos"); // Agrega un log de información al test
        registerPage.escribirUsername("usuario@dominio.com"); // Ingresa un usuario válido (reemplaza con tu usuario)
        Thread.sleep(1000); // Espera 1 segundo
        registerPage.escribirPassword("Digital.1856"); // Ingresa una contraseña válida (reemplaza con tu contraseña)
        Thread.sleep(1000); // Espera 1 segundo
        registerPage.confirmPassword("Digital.1856");
        test.log(Status.PASS, "Se ingresaron los datos correctamente"); // Agrega un log de paso al test
       registerPage.clickCaptcha();
        Assertions.assertEquals(registerPage.registerOk(), "Buscar");
        test.log(Status.PASS, "Se inicio sesión exitosamente"); // Agrega otro log de paso al test
    }
}
