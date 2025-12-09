package com.assessement.ExercicioQuatro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
    Page Object - Página Inicial
    Representa a home page do site automationexercise.com
*/
public class HomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Localizadores (seletores dos elementos)
    private By linkSignupLogin = By.cssSelector("a[href='/login']");
    
    // Construtor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Ações da página
    public void acessar() {
        driver.get("https://automationexercise.com");
    }
    
    public void clicarSignupLogin() {
        wait.until(ExpectedConditions.presenceOfElementLocated(linkSignupLogin));
        WebElement botao = driver.findElement(linkSignupLogin);
        botao.click();
    }
    
    public boolean isHomePageCarregada() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(linkSignupLogin)) != null;
    }
}
