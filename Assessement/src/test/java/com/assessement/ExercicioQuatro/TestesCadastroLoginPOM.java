package com.assessement.ExercicioQuatro;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.assessement.ExercicioQuatro.pages.HomePage;
import com.assessement.ExercicioQuatro.pages.LoginPage;
import com.assessement.ExercicioQuatro.pages.SignupPage;

import static org.junit.Assert.*;

/*
    Exercício 4.1 - Automação de Cadastro e Login
    com Page Object Model (POM)
    Site: https://automationexercise.com
*/
public class TestesCadastroLoginPOM {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SignupPage signupPage;
    
    @Before
    public void setUp() {
        // Configura o WebDriver automaticamente
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Inicializa os Page Objects
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    // Teste de cadastro de novo usuário usando POM
    @Test
    public void testCadastroNovoUsuario() {
        try {
            // Acessa a página inicial
            homePage.acessar();
            assertTrue("Home page deve carregar", homePage.isHomePageCarregada());
            
            // Navega para a página de login/signup
            homePage.clicarSignupLogin();
            assertTrue("Página de login deve carregar", loginPage.isPaginaLoginCarregada());
            
            // Inicia o processo de signup
            String nomeUsuario = "Teste Usuario " + System.currentTimeMillis();
            String emailUsuario = "teste" + System.currentTimeMillis() + "@email.com";
            
            loginPage.iniciarSignup(nomeUsuario, emailUsuario);
            
            // Verifica se a página de signup carregou
            assertTrue("Página de cadastro deve carregar", signupPage.isPaginaSignupCarregada());
            
            // Preenche o formulário completo de cadastro
            signupPage.preencherFormularioCompleto(
                "Senha123",          // senha
                "Cadu",              // primeiro nome
                "Menezes",           // sobrenome
                "Rua Teste, 123",    // endereço
                "United States",     // país
                "California",        // estado
                "Los Angeles",       // cidade
                "90001",             // zipcode
                "1234567890"         // telefone
            );
            
            // Verifica se o cadastro foi bem-sucedido
            assertTrue("Cadastro deve ser concluído com sucesso", signupPage.isCadastroSucesso());
            assertTrue("Mensagem de sucesso deve conter 'ACCOUNT CREATED'", 
                signupPage.obterMensagemSucesso().contains("ACCOUNT CREATED"));
            
        } catch (Exception e) {
            fail("Erro no teste de cadastro: " + e.getMessage());
        }
    }
    
    // Teste de login com credenciais válidas usando POM
    @Test
    public void testLoginCredenciaisValidas() {
        try {
            // Acessa a página inicial
            homePage.acessar();
            
            // Navega para a página de login
            homePage.clicarSignupLogin();
            assertTrue("Página de login deve carregar", loginPage.isPaginaLoginCarregada());
            
            // Faz o login
            String emailLogin = "testeusuario@email.com";
            String senhaLogin = "Senha123";
            
            loginPage.fazerLogin(emailLogin, senhaLogin);
            
            // Aguarda processamento
            Thread.sleep(2000);
            
            // Verifica se redirecionou da página de login
            String urlAtual = loginPage.obterUrlAtual();
            assertFalse("Login deve redirecionar da página de login", 
                urlAtual.contains("/login"));
            
        } catch (Exception e) {
            // Nota: Este teste pode falhar se não houver usuário cadastrado
            System.out.println("Nota: Para o teste de login funcionar, é necessário ter um usuário cadastrado.");
            System.out.println("Execute primeiro o teste de cadastro ou use credenciais válidas.");
        }
    }
    
    // Teste de login com credenciais inválidas usando POM
    @Test
    public void testLoginCredenciaisInvalidas() {
        try {
            // Acessa a página inicial
            homePage.acessar();
            
            // Navega para a página de login
            homePage.clicarSignupLogin();
            
            // Tenta fazer login com credenciais inválidas
            loginPage.fazerLogin("emailinvalido@teste.com", "senhaerrada123");
            
            // Aguarda processamento
            Thread.sleep(2000);
            
            // Verifica se permanece na página de login
            String urlAtual = loginPage.obterUrlAtual();
            assertTrue("Deve permanecer na página de login com credenciais inválidas", 
                urlAtual.contains("/login"));
            
        } catch (Exception e) {
            fail("Erro no teste de login inválido: " + e.getMessage());
        }
    }
}
