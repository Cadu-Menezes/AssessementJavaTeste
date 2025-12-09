package com.assessement.ExercicioTres;

import org.junit.Test;
import static org.junit.Assert.*;

/*
    Exercício 3.2 - Testes para consulta por endereço
    Formato: /ws/UF/cidade/logradouro/json/
*/
public class TestesConsultaEndereco {

    // Teste com endereço válido completo
    @Test
    public void testEnderecoValido() {
        try {
            String response = ViaCepService.buscarEndereco("SP", "Sao Paulo", "Avenida Paulista");
            
            assertNotNull("Response não deve ser nulo", response);
            assertTrue("Response deve conter dados", response.length() > 0);
            assertTrue("Response deve conter 'Paulista'", 
                response.toLowerCase().contains("paulista"));
            
        } catch (Exception e) {
            fail("Não deveria lançar exceção para endereço válido: " + e.getMessage());
        }
    }
    
    // Teste com UF inválida
    @Test
    public void testUfInvalida() {
        try {
            String response = ViaCepService.buscarEndereco("XX", "Sao Paulo", "Paulista");
            
            // API retorna array vazio [] para UF inválida
            assertTrue("Response deve indicar erro ou estar vazio", 
                response.contains("[]") || response.contains("erro"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita para UF inválida", e);
        }
    }
    
    // Teste com cidade inválida
    @Test
    public void testCidadeInvalida() {
        try {
            String response = ViaCepService.buscarEndereco("SP", "CidadeQueNaoExiste", "Rua");
            
            assertTrue("Response deve estar vazio ou indicar erro", 
                response.contains("[]") || response.contains("erro"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita para cidade inválida", e);
        }
    }
    
    // Teste com logradouro inválido
    @Test
    public void testLogradouroInvalido() {
        try {
            String response = ViaCepService.buscarEndereco("SP", "Sao Paulo", "RuaInexistenteXYZ123");
            
            assertTrue("Response deve estar vazio para logradouro inexistente", 
                response.contains("[]") || response.length() < 10);
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita para logradouro inválido", e);
        }
    }
    
    // Teste com todos os parâmetros vazios
    @Test
    public void testParametrosVazios() {
        try {
            ViaCepService.buscarEndereco("", "", "");
            fail("Deveria lançar exceção para parâmetros vazios");
            
        } catch (Exception e) {
            assertNotNull("Exceção esperada para parâmetros vazios", e);
        }
    }
    
    // Teste com parâmetros nulos
    @Test
    public void testParametrosNulos() {
        try {
            ViaCepService.buscarEndereco(null, null, null);
            fail("Deveria lançar exceção para parâmetros nulos");
            
        } catch (Exception e) {
            assertNotNull("Exceção esperada para parâmetros nulos", e);
        }
    }
    
    // Teste com caracteres especiais no logradouro
    @Test
    public void testLogradouroComCaracteresEspeciais() {
        try {
            String response = ViaCepService.buscarEndereco("SP", "Sao Paulo", "Rua @#$%");
            
            assertTrue("Response deve estar vazio ou indicar erro", 
                response.contains("[]") || response.contains("erro"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita para caracteres especiais", e);
        }
    }
}
