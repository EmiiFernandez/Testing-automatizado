import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class RegisterPage extends BasePage{
    protected RegisterPage(WebDriver driver) {
        super(driver);
    }

    // Localizadores para elementos de la página de registro
    private By username = By.id("username");
    private By password = By.id("new_password");
    private By confirmPassword = By.id("confirmed_password");
    private By creacionUser = By.id("form_search_h1");
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

    // Método para escribir la contraseña con simulación de escritura lenta
    public void confirmPassword(String pass) throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement passwordElement = driver.findElement(confirmPassword);

        for (char c : pass.toCharArray()) {
            actions.sendKeys(passwordElement, String.valueOf(c)).pause(50).build().perform();
        }

        // Se agregó una pausa adicional de 1 segundo  y el envío de la tecla ENTER
        Thread.sleep(1000);
        this.sendKey(Keys.ENTER, confirmPassword);
    }

    public String registerOk() {
        return this.getText(creacionUser);
    }

    // Método para cerrar una ventana emergente)
    public void closePopup() throws InterruptedException {
        closePopupWindow(closePopupButton, 4);
    }

    // Método para clickear Captcha de forma manual
    public void clickCaptcha() throws InterruptedException {
        boolean captchaClicked = false;
        int waitTime = 10;

        while (!captchaClicked) {
            System.out.println("Clickear manualmente el botón Captcha");
            if (driver.findElement(notRobot).isSelected()) {
                captchaClicked = true;
            } else {
                Thread.sleep(waitTime * 1000);
            }
        }
    }
}
