package com.assessement.ExercicioQuatro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
    Page Object - Página de Cadastro (Signup)
    Representa o formulário completo de cadastro de novo usuário
*/
public class SignupPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Localizadores - Informações da conta
    private By radioGeneroMasculino = By.id("id_gender1");
    private By campoSenha = By.id("password");
    
    // Localizadores - Informações pessoais
    private By campoPrimeiroNome = By.id("first_name");
    private By campoUltimoNome = By.id("last_name");
    private By campoEndereco = By.id("address1");
    private By campoPais = By.id("country");
    private By campoEstado = By.id("state");
    private By campoCidade = By.id("city");
    private By campoZipcode = By.id("zipcode");
    private By campoTelefone = By.id("mobile_number");
    
    // Localizadores - Botão e mensagens
    private By botaoCriarConta = By.cssSelector("button[data-qa='create-account']");
    private By mensagemSucesso = By.cssSelector("h2[data-qa='account-created']");
    
    // Construtor
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Ações - Preenchimento do formulário
    public void selecionarGeneroMasculino() {
        wait.until(ExpectedConditions.presenceOfElementLocated(radioGeneroMasculino));
        driver.findElement(radioGeneroMasculino).click();
    }
    
    public void preencherSenha(String senha) {
        driver.findElement(campoSenha).sendKeys(senha);
    }
    
    public void preencherPrimeiroNome(String nome) {
        driver.findElement(campoPrimeiroNome).sendKeys(nome);
    }
    
    public void preencherUltimoNome(String sobrenome) {
        driver.findElement(campoUltimoNome).sendKeys(sobrenome);
    }
    
    public void preencherEndereco(String endereco) {
        driver.findElement(campoEndereco).sendKeys(endereco);
    }
    
    public void selecionarPais(String pais) {
        driver.findElement(campoPais).sendKeys(pais);
    }
    
    public void preencherEstado(String estado) {
        driver.findElement(campoEstado).sendKeys(estado);
    }
    
    public void preencherCidade(String cidade) {
        driver.findElement(campoCidade).sendKeys(cidade);
    }
    
    public void preencherZipcode(String zipcode) {
        driver.findElement(campoZipcode).sendKeys(zipcode);
    }
    
    public void preencherTelefone(String telefone) {
        driver.findElement(campoTelefone).sendKeys(telefone);
    }
    
    public void clicarCriarConta() {
        driver.findElement(botaoCriarConta).click();
    }
    
    // Método auxiliar para preencher formulário completo
    public void preencherFormularioCompleto(String senha, String nome, String sobrenome, 
                                           String endereco, String pais, String estado, 
                                           String cidade, String zipcode, String telefone) {
        selecionarGeneroMasculino();
        preencherSenha(senha);
        preencherPrimeiroNome(nome);
        preencherUltimoNome(sobrenome);
        preencherEndereco(endereco);
        selecionarPais(pais);
        preencherEstado(estado);
        preencherCidade(cidade);
        preencherZipcode(zipcode);
        preencherTelefone(telefone);
        clicarCriarConta();
    }
    
    // Validações
    public boolean isPaginaSignupCarregada() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(radioGeneroMasculino)) != null;
    }
    
    public boolean isCadastroSucesso() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(mensagemSucesso));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String obterMensagemSucesso() {
        try {
            WebElement elemento = driver.findElement(mensagemSucesso);
            return elemento.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
