import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class SearchTest {
    // Instancia de WebDriver para interactuar con el navegador
    public WebDriver driver;

    // Prueba de búsqueda exitosa - Test en Booking
    @Test
    public void busquedaExitosa() throws InterruptedException {

        // Inicializar ChromeDriver con las opciones configuradas
        driver = new ChromeDriver();

        // Modificar los parámetros del navegador
        driver.manage().window().maximize(); // Maximizo el tamaño de la pantalla donde estamos haciendo el test

        // Ingresar a la página
        driver.get("https://www.booking.com/");
        Thread.sleep(1000);

        // Encontrar el botón de cierre de la ventana emergente
        // busca un elemento button que tenga un atributo aria-label con el valor "Ignorar información sobre el inicio de sesión."
        WebElement closeButton = driver.findElement(By.xpath("//button[@aria-label='Ignorar información sobre el inicio de sesión.']"));

        // Hacer clic en el botón de cierre
        closeButton.click();

        // Interacción con la barra de búsqueda. Selección de la barra
        WebElement searchBox = driver.findElement(By.name("ss"));

        // Escribir en el campo de búsqueda
        searchBox.sendKeys("Punta del Este");
        Thread.sleep(1000);

        // Seleccionar la primera opción del listado con un clic
        WebElement searchList = driver.findElement(By.id("autocomplete-result-0"));
        Thread.sleep(1000);
        searchList.click();

        searchBox.sendKeys(Keys.ENTER);
    }
}
