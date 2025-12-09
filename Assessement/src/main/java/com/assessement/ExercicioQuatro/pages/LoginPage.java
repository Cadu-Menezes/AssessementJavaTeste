package com.assessement.ExercicioQuatro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
    Page Object - Página de Login
    Representa a página de login/signup do site
*/
public class LoginPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Localizadores - Seção de Login
    private By campoEmailLogin = By.cssSelector("input[data-qa='login-email']");
    private By campoSenhaLogin = By.cssSelector("input[data-qa='login-password']");
    private By botaoLogin = By.cssSelector("button[data-qa='login-button']");
    
    // Localizadores - Seção de Signup
    private By campoNomeSignup = By.name("name");
    private By campoEmailSignup = By.cssSelector("input[data-qa='signup-email']");
    private By botaoSignup = By.cssSelector("button[data-qa='signup-button']");
    
    // Localizadores - Mensagens
    private By mensagemErro = By.cssSelector("p[style*='color: red']");
    
    // Construtor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Ações - Login
    public void preencherEmailLogin(String email) {
        wait.until(ExpectedConditions.presenceOfElementLocated(campoEmailLogin));
        WebElement campo = driver.findElement(campoEmailLogin);
        campo.clear();
        campo.sendKeys(email);
    }
    
    public void preencherSenhaLogin(String senha) {
        WebElement campo = driver.findElement(campoSenhaLogin);
        campo.clear();
        campo.sendKeys(senha);
    }
    
    public void clicarBotaoLogin() {
        WebElement botao = driver.findElement(botaoLogin);
        botao.click();
    }
    
    public void fazerLogin(String email, String senha) {
        preencherEmailLogin(email);
        preencherSenhaLogin(senha);
        clicarBotaoLogin();
    }
    
    // Ações - Signup
    public void preencherNomeSignup(String nome) {
        wait.until(ExpectedConditions.presenceOfElementLocated(campoNomeSignup));
        WebElement campo = driver.findElement(campoNomeSignup);
        campo.sendKeys(nome);
    }
    
    public void preencherEmailSignup(String email) {
        WebElement campo = driver.findElement(campoEmailSignup);
        campo.sendKeys(email);
    }
    
    public void clicarBotaoSignup() {
        WebElement botao = driver.findElement(botaoSignup);
        botao.click();
    }
    
    public void iniciarSignup(String nome, String email) {
        preencherNomeSignup(nome);
        preencherEmailSignup(email);
        clicarBotaoSignup();
    }
    
    // Validações
    public boolean isPaginaLoginCarregada() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(campoEmailLogin)) != null;
    }
    
    public boolean temMensagemErro() {
        try {
            return driver.findElement(mensagemErro) != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String obterMensagemErro() {
        try {
            return driver.findElement(mensagemErro).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String obterUrlAtual() {
        return driver.getCurrentUrl();
    }
}
