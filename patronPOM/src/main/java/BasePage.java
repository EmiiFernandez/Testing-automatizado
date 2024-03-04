import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//Superclase
public class BasePage {
    //Inicializar WebDriver para interactuar con el navegador
    public WebDriver driver;

    // Constructor de la clase BasePage que inicializa el WebDriver
    // Recibe un objeto WebDriver y lo asigna al atributo driver de la clase.
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Método setup() que configura el WebDriver para utilizar
    // ChromeDriver y maximiza la ventana
    public void setup() {
        // Se inicializa el WebDriver con ChromeDriver
       // driver = new ChromeDriver();
        // Se maximiza la ventana del navegador
        driver.manage().window().maximize();
    }

    // Método url(String url) que navega a una URL específica
    public void url(String url) throws InterruptedException {
        // Se carga la URL proporcionada en el navegador
        driver.get(url);
        Thread.sleep(1000);

    }

    // Cerrar navegador
    public void close() {
        driver.quit();
    }

    // Método para encontrar un elemento en la página mediante un
    // localizador (By)
    public WebElement findElement(By locator) {
        // Utiliza el método findElement() del WebDriver para localizar el
        // elemento en la página
        return driver.findElement(locator);
    }

    // Método para ingresar texto en un elemento de la página web
    public void sendText(String inputText, By locator) {
        // Se localiza el elemento en la página y se limpia el texto existente en el elemento
        this.findElement(locator).clear();
        // Se ingresa el nuevo texto en el elemento
        this.findElement(locator).sendKeys(inputText);
    }

    // Método para enviar una tecla específica a un elemento de la página web
    public void sendKey(CharSequence key, By locator) {
        // Se localiza el elemento en la página y se envía la tecla especificada al elemento
        this.findElement(locator).sendKeys(key);
    }

    // Método para hacer clic en un elemento de la página web
    public void click(By locator) {
        // Se localiza el elemento en la página y se hace clic en él
        this.findElement(locator).click();
    }

    // Método para obtener el texto de un elemento de la página web
    public String getText(By locator) {
        // Se localiza el elemento en la página y se obtiene su texto
        return  this.findElement(locator).getText();
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