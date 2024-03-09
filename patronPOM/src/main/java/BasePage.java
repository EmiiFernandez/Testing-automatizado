import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


//Superclase
public class BasePage {
    //Inicializar WebDriver para interactuar con el navegador
    protected WebDriver driver;

    // Atributo para realizar esperas explícitas en la página
    protected WebDriverWait wait;

    // Constructor de la clase BasePage que inicializa el WebDriver
    // Recibe un objeto WebDriver y lo asigna al atributo driver de la clase.
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        //Configurar la espera del wait
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /// Método para configurar el WebDriver y maximizar la ventana
    public void setup() {
    // Se inicializa el WebDriver con ChromeDriver
    // driver = new ChromeDriver();
    // Se maximiza la ventana del navegador
        driver.manage().window().maximize();
    }

    // Método para navegar a una URL específica
    public void url(String url) throws InterruptedException {
    // Se carga la URL proporcionada en el navegador
        driver.get(url);
    // Se espera a que la página cargue
        Thread.sleep(1000);
    }

    // Método para cerrar el navegador web
    public void close() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    // Método para encontrar un elemento en la página mediante un localizador
    protected WebElement findElement(By locator) {
    // Se utiliza el método findElement() del WebDriver para localizar el
    // elemento en la página
        return driver.findElement(locator);
    }

    // Método para ingresar texto en un elemento de la página web
    protected void sendText(String inputText, By locator) {
    // Se localiza el elemento en la página y se limpia el texto
    // existente en el elemento
        this.findElement(locator).clear();
    // Se ingresa el nuevo texto en el elemento
        this.findElement(locator).sendKeys(inputText);
    // Se espera a que el texto sea ingresado
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Método para enviar una tecla específica a un elemento de la página web
    protected void sendKey(CharSequence key, By locator) {
    // Se espera a que el elemento sea visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    // Se localiza el elemento en la página y se envía la tecla especificada al elemento
        this.findElement(locator).sendKeys(key);
    }

    // Método para hacer clic en un elemento de la página web
    public void click(By locator) throws InterruptedException {
    // Se espera a que el elemento sea clickeable
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    // Método para obtener el texto de un elemento de la página web
    public String getText(By locator)  {
    // Se espera a que el elemento sea visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    // Se localiza el elemento en la página y se obtiene su texto
        return this.findElement(locator).getText();
    }

    // Método para cerrar ventanas emergentes si están presentes
    public void closePopupWindow(By closeButtonLocator, int maxAttempts) throws InterruptedException {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                WebElement closeButton = driver.findElement(closeButtonLocator);
                closeButton.click();
                break;
            } catch (NoSuchElementException e) {
    // Popup window is not present, exit the loop
                Thread.sleep(1000);
                attempts++;
            }
        }
    }
}