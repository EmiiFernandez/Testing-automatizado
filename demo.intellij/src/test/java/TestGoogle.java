import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGoogle {
    //Instanciar el driver de webdriver
    public WebDriver driver;

    //JUnit - Test basica para correr el test
    @Test

    public void test_1() {
        //abrir navegador
        driver = new ChromeDriver();

       //busqueda url
        driver.get("https://google.com");

        //chequeamos que ingresamos correctamente
        //solicitamos el titulo del HTML de la pag
        driver.getTitle(); //"Google"

        //interactuar con los elementos con WebElement
        //nos permite crear variables donde depositaremos
        //un elemento puntual de la pantalla

        //interaccion con la barra de busqueda
        //By librería de selenium. name --> buscamos por el nombre
        //q es el name en HTML de Google
        WebElement searchBox = driver.findElement(By.name("q"));

        //clickeamos en la barra de busqueda
        WebElement searchButtom = driver.findElement(By.name("btnK"));

        //escribo Selenium en la barra de busqueda
        searchBox.sendKeys("Selenium");
        //clickeo en el boton de buscar - no funciono por que el boton queda oculto
        //searchButtom.click();

        //damos enter a la busqueda
        //usamos la libreria keys para simular el teclado
        searchBox.sendKeys(Keys.ENTER);

        //como cambia de pagina volvemos a capturar el name del searchBox
        searchBox = driver.findElement(By.name("q"));

        //chequeo la busqueda con un atributo
        searchBox.getAttribute("value"); // Selenium

        //cerrar navegador
        driver.quit();


    }
}
