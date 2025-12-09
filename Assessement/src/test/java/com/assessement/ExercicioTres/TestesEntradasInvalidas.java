package com.assessement.ExercicioTres;

import org.junit.Test;
import static org.junit.Assert.*;

/*
    Exercício 3.1 - Testes para entradas inválidas ou inexistentes
    Testando CEPs com letras, vazios ou números inválidos
*/
public class TestesEntradasInvalidas {

    // Teste com CEP contendo letras
    @Test
    public void testCepComLetras() {
        try {
            String response = ViaCepService.buscarCep("abc12345");
            
            // ViaCEP retorna erro em formato JSON quando CEP é inválido
            assertTrue("Response deve indicar erro", 
                response.contains("erro") || response.contains("error"));
            
        } catch (Exception e) {
            // Aceita exceção pois é entrada inválida
            assertNotNull("Exceção esperada para CEP com letras", e);
        }
    }
    
    // Teste com CEP vazio
    @Test
    public void testCepVazio() {
        try {
            ViaCepService.buscarCep("");
            fail("Deveria lançar exceção para CEP vazio");
            
        } catch (Exception e) {
            // Exceção esperada
            assertNotNull("Exceção esperada para CEP vazio", e);
        }
    }
    
    // Teste com CEP com formato inválido (muito curto)
    @Test
    public void testCepCurto() {
        try {
            String response = ViaCepService.buscarCep("123");
            
            assertTrue("Response deve indicar erro para CEP curto", 
                response.contains("erro") || response.contains("error"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita para CEP curto", e);
        }
    }
    
    // Teste com CEP com formato inválido (muito longo)
    @Test
    public void testCepLongo() {
        try {
            String response = ViaCepService.buscarCep("123456789012345");
            
            assertTrue("Response deve indicar erro para CEP longo", 
                response.contains("erro") || response.contains("error"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita para CEP longo", e);
        }
    }
    
    // Teste com CEP inexistente (formato válido mas não existe)
    @Test
    public void testCepInexistente() {
        try {
            String response = ViaCepService.buscarCep("99999999");
            
            // ViaCEP retorna {"erro": true} quando CEP não existe
            assertTrue("Response deve conter indicação de erro", 
                response.contains("erro"));
            
        } catch (Exception e) {
            assertNotNull("Resposta de erro esperada", e);
        }
    }
    
    // Teste com CEP nulo
    @Test
    public void testCepNulo() {
        try {
            ViaCepService.buscarCep(null);
            fail("Deveria lançar exceção para CEP nulo");
            
        } catch (Exception e) {
            // Exceção esperada (NullPointerException ou similar)
            assertNotNull("Exceção esperada para CEP nulo", e);
        }
    }
    
    // Teste com caracteres especiais
    @Test
    public void testCepComCaracteresEspeciais() {
        try {
            String response = ViaCepService.buscarCep("01310@#$");
            
            assertTrue("Response deve indicar erro", 
                response.contains("erro") || response.contains("error"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita para caracteres especiais", e);
        }
    }
}
