package reportes;

import com.aventstack.extentreports.ExtentReports;

// Clase diseñada para instanciar objetos ExtentReports para la generación de reportes
public class ExtentFactory {

    // Método que crea y configura un objeto ExtentReports
    public static ExtentReports getInstance() {
        ExtentReports extent = new ExtentReports();

        // Agrega información del sistema a la configuración del reporte
        extent.setSystemInfo("OS", "Windows");  // Indica el sistema operativo utilizado
        extent.setSystemInfo("Navegador", "Chrome"); // Indica el navegador web utilizado
        extent.setSystemInfo("Ambiente", "QA"); // Indica el ambiente de ejecución de las pruebas

        return extent; // Devuelve el objeto ExtentReports configurado
    }
}