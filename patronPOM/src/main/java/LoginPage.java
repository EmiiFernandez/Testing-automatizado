import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
// Librerías para interactuar con el navegador
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
// Librerías para acciones más complejas (como simular escritura lenta)
import org.openqa.selenium.support.ui.ExpectedConditions;
// Librería para esperas inteligentes en pruebas

public class LoginPage extends BasePage { // La clase LoginPage hereda de una clase BasePage (no mostrada)

    protected LoginPage(WebDriver driver) {
        super(driver);  // Llama al constructor de la clase padre
    }

    // Localizadores para elementos de la página de login
    private By username = By.id("username");
    private By password = By.id("password");
    private By emailRequired = By.id("username-note");
    private By passwordRequired = By.id("password-note");
    private By iconLogin = By.className("e4adce92df");
    private By closePopupButton = By.xpath("//button[@aria-label='Ignorar información sobre el inicio de sesión.']");
    private By notRobot = By.xpath("//div[@aria-label='Press and hold'][1]//p[text()='Press and hold']");

    // Método para escribir el nombre de usuario con simulación de escritura lenta
    public void escribirUsername(String user) throws InterruptedException {
        Actions actions = new Actions(driver);  // Crea una instancia de Actions para realizar acciones complejas
        WebElement usernameElement = driver.findElement(username);  // Busca el elemento del nombre de usuario

        for (char c : user.toCharArray()) {
            actions.sendKeys(usernameElement, String.valueOf(c)).pause(50).build().perform();  // Envía cada caracter con una pausa
        }

        // Se agregó una pausa adicional de 1 segundo (ajustar según sea necesario) y el envío de la tecla ENTER
        Thread.sleep(1000);
        this.sendKey(Keys.ENTER, username);
    }

    // Método para escribir la contraseña con simulación de escritura lenta
    public void escribirPassword(String pass) throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement passwordElement = driver.findElement(password);

        for (char c : pass.toCharArray()) {
            actions.sendKeys(passwordElement, String.valueOf(c)).pause(50).build().perform();
        }

        // Se agregó una pausa adicional de 1 segundo (ajustar según sea necesario) y el envío de la tecla ENTER
        Thread.sleep(1000);
        this.sendKey(Keys.ENTER, password);
    }

    // Método para verificar si el login fue exitoso
    public String logueado() {
        String message;
        if (iconLogin != null) {
            message = "Usuario logueado con éxito";
        } else {
            message = "Hubo un problema al intentar iniciar sesión";
        }

        System.out.println(message);
        return message;
    }

    // Método para obtener el mensaje de error de usuario inválido
    public String usernameInvalido() {
        System.out.println("MENSAJE DE ERROR: " + this.getText(emailRequired));
        return this.getText(emailRequired);
    }

    // Método para obtener el mensaje de error de contraseña inválida
    public String passwordInvalida() {
        System.out.println("MENSAJE DE ERROR: " + this.getText(passwordRequired));
        return this.getText(passwordRequired);
    }

    // Método para cerrar una ventana emergente (asume la existencia de un método closePopupWindow en otro lugar)
   // public void closePopup() throws InterruptedException {
    //    closePopupWindow(closePopupButton, 4);
    //}

    public void closePopup() throws InterruptedException {
        this.closePopup(closePopupButton);
    }

    // Método para hacer clic y mantener presionado el elemento "no soy un robot"
    public void notRobot() throws InterruptedException {

        // Espera a que el elemento "no soy un robot" sea clickable
        wait.until(ExpectedConditions.elementToBeClickable(notRobot)).click();

        // Simula mantener el clic presionado por 2 segundos (opcional y ajustable)
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(notRobot)).perform();
        Thread.sleep(5000); // Modifica la duración según sea necesario
        actions.release(driver.findElement(notRobot)).perform();
    }
}