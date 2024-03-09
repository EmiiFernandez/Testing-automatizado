import org.openqa.selenium.*;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    //aclarar el tipo de variable va a recibir (By)
    private By searchBox = By.name("ss");
    private By searchOk = By.className("abf093bdfe");
    private By closePopupButton = By.xpath("//button[@aria-label='Ignorar información sobre el inicio de sesión.']");

    public void escribirBusqueda(String ciudad) throws InterruptedException {
        this.sendText(ciudad, searchBox);
        Thread.sleep(1000);
        this.sendKey(Keys.ARROW_DOWN, searchBox);
        this.sendKey(Keys.ENTER, searchBox);
        this.sendKey(Keys.ENTER, searchBox);
    }

    public String searchBoxText() {
        return this.findElement(searchBox).getAttribute("value");
    }

    public void resultadoBusqueda()  {
        String searchBoxText = searchBoxText(); // Llama al método para obtener el texto
        if (searchBoxText != null && !searchBoxText.isEmpty() && searchBox != null) {
            System.out.println("Búsqueda exitosa: " + searchBoxText);
            System.out.println("RESULTADO DE LA BUSQUEDA: " + this.getText(searchOk));
        } else {
            System.out.println("Hubo un error al realizar la búsqueda");
        }
    }

    // Método para cerrar la ventana emergente
    public void closePopup() throws InterruptedException {
        closePopupWindow(closePopupButton, 4);
    }

}