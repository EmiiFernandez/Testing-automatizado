import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;


public class SearchTestPOM {

    @Test
    public void busquedaExitosa() throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();

        SearchPage searchPage = new SearchPage(driver);

        
        searchPage.setup();

        searchPage.url("https://www.booking.com/");

        searchPage.closePopup();

        searchPage.escribirBusqueda("Punta del Este");

        Thread.sleep(1000);

        searchPage.resultadoBusqueda();

        Thread.sleep(3000);

        searchPage.close();

    }
}
