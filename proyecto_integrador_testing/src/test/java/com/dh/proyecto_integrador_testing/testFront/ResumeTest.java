package com.dh.proyecto_integrador_testing.testFront;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.dh.proyecto_integrador_testing.page.ResumenPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ExtentFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResumeTest { private static WebDriver driver;
    private static WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/resume-report.html");

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
    void AperturadecuentaExitosa() throws InterruptedException {
        ExtentTest test = extent.createTest("Prueba de resumen de cuenta Exitosa");
        test.log(Status.INFO, "Comienza el Test");

        ResumenPage resumenPage = new ResumenPage(driver, wait);
        resumenPage.setup();
        resumenPage.open("https://parabank.parasoft.com/parabank/index.htm");
        test.log(Status.PASS, "Se ha ingresado exitosamente a la pagina.");

        resumenPage.escribirUsuario("userTest");
        resumenPage.escribirPassword("passTest");
        resumenPage.clickBotonLogin();

        resumenPage.clickAccountsOverview();


        assertTrue(resumenPage.obtenerMensajeBalance().contains( "*Balance includes deposits that may be subject to holds"));
        test.log(Status.PASS, "Se visualizó el resumen de cuentas exitosamente.");

    }

    /*
    @AfterEach
    public void cerrar() {
        NewAccountPage searchPage = new NewAccountPage(driver, wait);
        searchPage.close();
    }*/

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }
}
