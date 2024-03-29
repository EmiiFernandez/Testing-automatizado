package com.dh.proyecto_integrador_testing.page;

import com.dh.proyecto_integrador_testing.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountPage extends BasePage {


    private By usuarioId = By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input");
    private By password = By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input");
    private By botonLogin = By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input");
    private By openNewAccount = By.linkText("Open New Account");
    private By tipoCuentaId = By.id("type");
    private By botonSubmit = By.xpath("//INPUT[@type='submit']");


    private By cuentaExitosa = By.xpath("//P[text()='Congratulations, your account is now open.']");
    //Congratulations, your account is now open.


    public NewAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void escribirUsuario(String usuario) throws InterruptedException {
        sendText(usuario, usuarioId);
    }

    public void escribirPassword(String pass) throws InterruptedException {
        sendText(pass, password);
    }

    public void clickBotonLogin() throws InterruptedException {
        this.clickear(botonLogin);
    }

    public void clickOpenNewAccount() throws InterruptedException {
        this.clickear(openNewAccount);
    }

    public void seleccionarTipoCuenta() throws InterruptedException {
        Select select = new Select(driver.findElement(tipoCuentaId));
        select.selectByVisibleText("SAVINGS");
    }

    public void clickSubmit() throws InterruptedException {
        this.clickear(botonSubmit);
    }

    // Mensajes

    public String obtenerMensajeExitoso() {
        System.out.println("MENSAJE: " + this.getText(cuentaExitosa));
        return this.getText(cuentaExitosa);
    }
}
