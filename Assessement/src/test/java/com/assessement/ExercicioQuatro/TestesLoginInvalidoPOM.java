package com.assessement.ExercicioQuatro;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.assessement.ExercicioQuatro.pages.HomePage;
import com.assessement.ExercicioQuatro.pages.LoginPage;

import static org.junit.Assert.*;

/*
    Exercício 4.2 - Testes de Login com Credenciais Inválidas
    com Page Object Model (POM)
    Verifica se as mensagens de erro apropriadas são exibidas
*/
public class TestesLoginInvalidoPOM {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Inicializa os Page Objects
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    // Método auxiliar para acessar a página de login
    private void acessarPaginaLogin() {
        homePage.acessar();
        homePage.clicarSignupLogin();
        assertTrue("Página de login deve carregar", loginPage.isPaginaLoginCarregada());
    }
    
    // Teste 1: Email e senha completamente inválidos
    @Test
    public void testLoginEmailSenhaInvalidos() {
        try {
            acessarPaginaLogin();
            
            loginPage.fazerLogin("usuarioinexistente@teste.com", "senhaerrada123");
            
            // Aguarda processamento
            Thread.sleep(2000);
            
            // Verifica se permanece na página de login
            assertTrue("Deve permanecer na página de login", 
                loginPage.obterUrlAtual().contains("/login"));
            
            // Verifica se há mensagem de erro
            String mensagem = loginPage.obterMensagemErro().toLowerCase();
            if (!mensagem.isEmpty()) {
                assertTrue("Mensagem deve indicar erro de login", 
                    mensagem.contains("incorrect") || mensagem.contains("wrong"));
            }
            
        } catch (Exception e) {
            fail("Erro no teste: " + e.getMessage());
        }
    }
    
    // Teste 2: Email vazio
    @Test
    public void testLoginEmailVazio() {
        try {
            acessarPaginaLogin();
            
            loginPage.fazerLogin("", "senha123");
            
            Thread.sleep(1500);
            
            // Verifica se permanece na página de login
            assertTrue("Deve permanecer na página de login com email vazio", 
                loginPage.obterUrlAtual().contains("/login"));
            
        } catch (Exception e) {
            fail("Erro no teste: " + e.getMessage());
        }
    }
    
    // Teste 3: Senha vazia
    @Test
    public void testLoginSenhaVazia() {
        try {
            acessarPaginaLogin();
            
            loginPage.fazerLogin("teste@email.com", "");
            
            Thread.sleep(1500);
            
            // Verifica se permanece na página de login
            assertTrue("Deve permanecer na página de login com senha vazia", 
                loginPage.obterUrlAtual().contains("/login"));
            
        } catch (Exception e) {
            fail("Erro no teste: " + e.getMessage());
        }
    }
    
    // Teste 4: Email e senha vazios
    @Test
    public void testLoginCamposVazios() {
        try {
            acessarPaginaLogin();
            
            loginPage.fazerLogin("", "");
            
            Thread.sleep(1500);
            
            // Verifica se permanece na página de login
            assertTrue("Deve permanecer na página de login com campos vazios", 
                loginPage.obterUrlAtual().contains("/login"));
            
        } catch (Exception e) {
            fail("Erro no teste: " + e.getMessage());
        }
    }
    
    // Teste 5: Email com formato inválido
    @Test
    public void testLoginEmailFormatoInvalido() {
        try {
            acessarPaginaLogin();
            
            // Email sem @ e sem domínio
            loginPage.fazerLogin("emailinvalido", "senha123");
            
            Thread.sleep(1500);
            
            // Verifica se permanece na página de login
            assertTrue("Deve permanecer na página de login com email inválido", 
                loginPage.obterUrlAtual().contains("/login"));
            
        } catch (Exception e) {
            fail("Erro no teste: " + e.getMessage());
        }
    }
    
    // Teste 6: Caracteres especiais na senha
    @Test
    public void testLoginSenhaCaracteresEspeciais() {
        try {
            acessarPaginaLogin();
            
            loginPage.fazerLogin("teste@email.com", "!@#$%^&*()");
            
            Thread.sleep(2000);
            
            // Verifica se permanece na página de login
            assertTrue("Deve permanecer na página de login", 
                loginPage.obterUrlAtual().contains("/login"));
            
        } catch (Exception e) {
            fail("Erro no teste: " + e.getMessage());
        }
    }
    
    // Teste 7: Email válido mas senha incorreta
    @Test
    public void testLoginEmailValidoSenhaIncorreta() {
        try {
            acessarPaginaLogin();
            
            // Usa um email com formato válido mas senha errada
            loginPage.fazerLogin("usuario.teste@gmail.com", "senhaincorreta999");
            
            Thread.sleep(2000);
            
            // Verifica se permanece na página de login
            assertTrue("Deve permanecer na página de login", 
                loginPage.obterUrlAtual().contains("/login"));
            
            // Verifica se há mensagem de erro
            if (loginPage.temMensagemErro()) {
                assertNotNull("Deve exibir mensagem de erro", 
                    loginPage.obterMensagemErro());
            }
            
        } catch (Exception e) {
            fail("Erro no teste: " + e.getMessage());
        }
    }
}
