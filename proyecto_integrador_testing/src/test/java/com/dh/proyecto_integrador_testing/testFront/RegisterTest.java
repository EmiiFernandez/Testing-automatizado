package com.dh.proyecto_integrador_testing.testFront;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.dh.proyecto_integrador_testing.page.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ExtentFactory;

import java.time.Duration;

public class RegisterTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/register-report.html");

    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
    }

    @Test
    public void registroExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Test registro exitoso");
        test.log(Status.INFO, "Comienza el test");

        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.open("https://parabank.parasoft.com/parabank/register.htm");

        test.log(Status.PASS, "Se ingreso correctamente a la página");

        registerPage.clickRegister();
        test.log(Status.PASS, "Se ingreso correctamente a la página de registro");

        //Completo formulario
        registerPage.escribirFistName("NameTest");
        registerPage.escribirLastName("LastNameTest");
        registerPage.escribirAddress("AddressTest");
        registerPage.escribirCity("CityTest");
        registerPage.escribirState("StateTest");
        registerPage.escribirZipCode("1236");
        registerPage.escribirPhone("123456789");
        registerPage.escribirSsn("123456789");
        registerPage.escribirUsername("userTest");
        registerPage.escribirPassword("passTest");
        registerPage.escribirPasswordConfirm("passTest");

        registerPage.clickSubmit();

        Assertions.assertEquals(registerPage.obtenerMensajeRegistrado(), "Your account was created successfully. You are now logged in.");

        test.log(Status.PASS, "Se realizo el registro correctamente");
    }

 /*   @AfterEach
    public void close() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }
*/
    @AfterAll
    public static void saveReport() {
        extent.flush();

    }
}
