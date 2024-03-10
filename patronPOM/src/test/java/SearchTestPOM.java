import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import reportes.ExtentFactory;

public class SearchTestPOM {
    // **Atributos de la clase para manejar el navegador y la página de búsqueda**

    private WebDriver driver; // Variable para almacenar la instancia del navegador
    private SearchPage searchPage; // Variable para almacenar la instancia de la página de búsqueda

    // **Objeto para la generación de reportes**

    public static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte.html"); // Genera un archivo HTML para el reporte

    public static ExtentReports extent; // Reporte principal

    //Creo el reporte en un BeforeAll para que se guarden cada uno de los test
    // y no se sobreescriban los test cuando realiza más de uno
    @BeforeAll
    public static void createReport() {
        // **Inicializa el objeto de reporte**

        extent = ExtentFactory.getInstance(); // Crea una instancia de la clase ExtentFactory para la configuración del reporte
        extent.attachReporter(info); // Agrega el "reporter" al reporte
    }

    // Método que se ejecuta antes de cada prueba para preparar el entorno
    @BeforeEach
    public void preconditions() throws InterruptedException {
        // **Configura el navegador y la página de búsqueda**

        // Crea una instancia del driver de Chrome para controlar el navegador
        driver = new ChromeDriver();

        // Crea una instancia de la clase SearchPage para interactuar con la página de búsqueda
        searchPage = new SearchPage(driver);

        // Prepara la página de búsqueda (implementación específica en la clase SearchPage)
        searchPage.setup();

        // Navega a la página principal de Booking.com
        searchPage.url("https://www.booking.com/");

        // Cierra cualquier popup o alerta que pueda aparecer
        searchPage.closePopup();
    }

    // **Prueba para verificar una búsqueda exitosa en Uruguay**

    @Test
    @Tag("BUSQUEDA") // Etiqueta para agrupar la prueba en la suite "BUSQUEDA"
    @Tag("SMOKE") // Etiqueta para agrupar la prueba en la suite "SMOKE"
    public void busquedaExitosa_Uruguay() throws InterruptedException {

        // **Crea un test dentro del reporte**

        ExtentTest test = extent.createTest("Búsqueda exitosa en Uruguay"); // Crea un nuevo test dentro del reporte con el nombre especificado
        test.log(Status.INFO, "Comienza nuestro test de búsqueda"); // Agrega un log de tipo informativo al test

        // **Realiza la búsqueda y registra información en el reporte**

        // Escribe "Punta del Este" en el campo de búsqueda
        searchPage.escribirBusqueda("Punta del Este");

        test.log(Status.PASS, "Se realizó la búsqueda de Uruguay exitosamente"); // Agrega un log de tipo "pass" al test

        // Realiza acciones con los resultados de la búsqueda (implementación específica en SearchPage)
        searchPage.resultadoBusqueda();

        test.log(Status.PASS, "Se valida que la búsqueda haya sido correcta"); // Agrega un log de tipo "pass" al test
    }

    @Test
    @Tag("BUSQUEDA")
    @Tag("REGRESSION")
    public void busquedaExitosa_Grecia() throws InterruptedException {
        ExtentTest test = extent.createTest("Búsqueda exitosa en Grecia"); // Crea un nuevo test dentro del reporte con el nombre especificado
        test.log(Status.INFO, "Comienza nuestro test de búsqueda"); // Agrega un log de tipo informativo al test

        searchPage.escribirBusqueda("Paros");

        test.log(Status.PASS, "Se realizó la búsqueda de Grecia exitosamente"); // Agrega un log de tipo "pass" al test

        searchPage.resultadoBusqueda();

        test.log(Status.PASS, "Se valida que la búsqueda haya sido correcta"); // Agrega un log de tipo "pass" al test

    }

    // **Método que se ejecuta después de cada prueba para cerrar el navegador**
    @AfterEach
    public void close() throws InterruptedException {
        searchPage = new SearchPage(driver);
        searchPage.close(); // Cierra el navegador
    }

    //Finalizo el reporte en un AfterAll para que no se sobreescriba
    //los test cuando se inicializa más de uno
    @AfterAll
    public static void saveReport() {
        extent.flush(); // Finaliza el reporte
    }
}
