package com.dh.proyecto_integrador_testing.page;

import com.dh.proyecto_integrador_testing.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
    protected By botonRegister = By.linkText("Register");
    private By firstNameId = By.id("customer.firstName");
    private By lastNameId = By.id("customer.lastName");
    private By addressId = By.id("customer.address.street");
    private By cityId = By.id("customer.address.city");
    private By stateId = By.id("customer.address.state");
    private By zipCodeId = By.id("customer.address.zipCode");
    private By phoneId = By.id("customer.phoneNumber");
    private By ssnId = By.id("customer.ssn");
    private By usernameId = By.id("customer.username");
    private By passwordId = By.id("customer.password");
    private By passwordConfirmId = By.id("repeatedPassword");
    private By botonSubmit = By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");
    private By registrado = By.xpath("//P[text()='Your account was created successfully. You are now logged in.']");


    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickRegister() {
        this.clickear(botonRegister);
    }

    public void escribirFistName(String firstName) {
        sendText(firstName, firstNameId);
    }

    public void escribirLastName(String lastName) {
        sendText(lastName, lastNameId);
    }

    public void escribirAddress(String address) {
        sendText(address, addressId);
    }

    public void escribirCity(String city) {
        sendText(city, cityId);
    }

    public void escribirState(String state) {
        sendText(state, stateId);
    }

    public void escribirZipCode(String zipCode) {
        sendText(zipCode, zipCodeId);
    }

    public void escribirPhone(String phone) {
        sendText(phone, phoneId);
    }

    public void escribirSsn(String ssn) {
        sendText(ssn, ssnId);
    }

    public void escribirUsername(String username) {
        sendText(username, usernameId);
    }
    public void escribirPassword(String password) {
        sendText(password, passwordId);
    }
    public void escribirPasswordConfirm(String passwordConfirm) {
        sendText(passwordConfirm, passwordConfirmId);
    }

    public void clickSubmit() throws InterruptedException {
        this.clickear(botonSubmit);
    }

    public String obtenerMensajeRegistrado() {
        System.out.println("MENSAJE: " + this.getText(registrado));
        return this.getText(registrado);
    }
}
