import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchTestPOM {
    // Atributos de la clase para manejar el navegador y la página de búsqueda
    private WebDriver driver;
    private SearchPage searchPage;

    // Método que se ejecuta antes de cada prueba para preparar el entorno
    @BeforeEach
    public void preconditions() throws InterruptedException {
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

    // Prueba para verificar una búsqueda exitosa en Uruguay
    @Test
    @Tag("BUSQUEDA") //Creación de suite
    @Tag("SMOKE") //Un test puede agregarse en distintos suites
    public void busquedaExitosa_Uruguay() throws InterruptedException {
        // Escribe "Punta del Este" en el campo de búsqueda
        searchPage.escribirBusqueda("Punta del Este");

        // Realiza acciones con los resultados de la búsqueda (implementación específica en SearchPage)
        searchPage.resultadoBusqueda();

    }

    @Test
    @Tag("BUSQUEDA")
    @Tag("REGRESSION")
    public void busquedaExitosa_Grecia() throws InterruptedException {

        searchPage.escribirBusqueda("Paros");

        searchPage.resultadoBusqueda();

    }

    @AfterEach
    public void close() throws InterruptedException {
        searchPage = new SearchPage(driver);

        searchPage.close();
    }
}
