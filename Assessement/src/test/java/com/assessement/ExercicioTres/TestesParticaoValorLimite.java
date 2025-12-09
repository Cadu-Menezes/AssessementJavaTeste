package com.assessement.ExercicioTres;

import org.junit.Test;
import static org.junit.Assert.*;

/*
    Exercício 3.4 - Partição de Equivalência e Análise de Valor Limite
    
    PARTIÇÃO DE EQUIVALÊNCIA para CEP:
    - Partição Válida: CEP com 8 dígitos numéricos (ex: "01310100")
    - Partição Inválida 1: CEP com menos de 8 dígitos (ex: "123")
    - Partição Inválida 2: CEP com mais de 8 dígitos (ex: "012345678")
    - Partição Inválida 3: CEP com letras (ex: "abc12345")
    - Partição Inválida 4: CEP vazio ou nulo
    
    ANÁLISE DE VALOR LIMITE para CEP (tamanho):
    - Limite inferior: 0 dígitos (vazio)
    - Valor logo acima: 1 dígito
    - Valor abaixo do válido: 7 dígitos
    - Valor válido mínimo: 8 dígitos
    - Valor válido máximo: 8 dígitos
    - Valor acima do válido: 9 dígitos
    - Limite superior: string muito longa
*/
public class TestesParticaoValorLimite {

    // ========================================
    // PARTIÇÃO DE EQUIVALÊNCIA - CEP
    // ========================================
    
    // Partição Válida: CEP de 8 dígitos
    @Test
    public void testParticaoValida_Cep8Digitos() {
        try {
            String response = ViaCepService.buscarCep("01310100");
            
            assertNotNull("Response não deve ser nulo", response);
            assertFalse("Não deve conter erro", response.contains("\"erro\""));
            assertTrue("Deve conter dados de endereço", 
                response.contains("logradouro") || response.contains("bairro"));
            
        } catch (Exception e) {
            fail("Não deveria lançar exceção para CEP válido: " + e.getMessage());
        }
    }
    
    // Partição Inválida 1: CEP com menos de 8 dígitos
    @Test
    public void testParticaoInvalida_CepCurto() {
        try {
            String response = ViaCepService.buscarCep("123");
            
            assertTrue("Deve indicar erro para CEP curto", 
                response.contains("erro") || response.contains("error"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita para CEP curto", e);
        }
    }
    
    // Partição Inválida 2: CEP com mais de 8 dígitos
    @Test
    public void testParticaoInvalida_CepLongo() {
        try {
            String response = ViaCepService.buscarCep("012345678");
            
            assertTrue("Deve indicar erro para CEP longo", 
                response.contains("erro") || response.contains("error"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita para CEP longo", e);
        }
    }
    
    // Partição Inválida 3: CEP com caracteres não numéricos
    @Test
    public void testParticaoInvalida_CepComLetras() {
        try {
            String response = ViaCepService.buscarCep("abc12345");
            
            assertTrue("Deve indicar erro para CEP com letras", 
                response.contains("erro") || response.contains("error"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita para CEP com letras", e);
        }
    }
    
    // Partição Inválida 4: CEP vazio
    @Test
    public void testParticaoInvalida_CepVazio() {
        try {
            ViaCepService.buscarCep("");
            fail("Deveria lançar exceção para CEP vazio");
            
        } catch (Exception e) {
            assertNotNull("Exceção esperada para CEP vazio", e);
        }
    }
    
    // ========================================
    // ANÁLISE DE VALOR LIMITE - Tamanho do CEP
    // ========================================
    
    // Valor Limite: 0 dígitos (vazio)
    @Test
    public void testValorLimite_0Digitos() {
        try {
            ViaCepService.buscarCep("");
            fail("Deveria lançar exceção para 0 dígitos");
            
        } catch (Exception e) {
            assertNotNull("Exceção esperada", e);
        }
    }
    
    // Valor Limite: 1 dígito (logo acima do limite inferior)
    @Test
    public void testValorLimite_1Digito() {
        try {
            String response = ViaCepService.buscarCep("1");
            
            assertTrue("Deve indicar erro para 1 dígito", 
                response.contains("erro"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita", e);
        }
    }
    
    // Valor Limite: 7 dígitos (logo abaixo do válido)
    @Test
    public void testValorLimite_7Digitos() {
        try {
            String response = ViaCepService.buscarCep("0131010");
            
            assertTrue("Deve indicar erro para 7 dígitos", 
                response.contains("erro"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita", e);
        }
    }
    
    // Valor Limite: 8 dígitos (valor válido mínimo e máximo)
    @Test
    public void testValorLimite_8Digitos() {
        try {
            String response = ViaCepService.buscarCep("01310100");
            
            assertNotNull("Response não deve ser nulo", response);
            assertFalse("Não deve conter erro", response.contains("\"erro\""));
            
        } catch (Exception e) {
            fail("Não deveria lançar exceção para 8 dígitos: " + e.getMessage());
        }
    }
    
    // Valor Limite: 9 dígitos (logo acima do válido)
    @Test
    public void testValorLimite_9Digitos() {
        try {
            String response = ViaCepService.buscarCep("013101000");
            
            assertTrue("Deve indicar erro para 9 dígitos", 
                response.contains("erro"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita", e);
        }
    }
    
    // Valor Limite: String muito longa (limite superior extremo)
    @Test
    public void testValorLimite_StringMuitoLonga() {
        try {
            String response = ViaCepService.buscarCep("01310100012345678901234567890");
            
            assertTrue("Deve indicar erro para string muito longa", 
                response.contains("erro"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita", e);
        }
    }
    
    // ========================================
    // PARTIÇÃO DE EQUIVALÊNCIA - UF
    // ========================================
    
    // Partição Válida: UF com 2 letras maiúsculas válidas
    @Test
    public void testParticaoValida_UfValida() {
        try {
            String response = ViaCepService.buscarEndereco("SP", "Sao Paulo", "Paulista");
            
            assertNotNull("Response não deve ser nulo", response);
            assertFalse("Deve retornar resultados", response.trim().equals("[]"));
            
        } catch (Exception e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
    }
    
    // Partição Inválida: UF com 2 letras mas inválida
    @Test
    public void testParticaoInvalida_UfInexistente() {
        try {
            String response = ViaCepService.buscarEndereco("XX", "Cidade", "Rua");
            
            assertTrue("Deve retornar lista vazia", 
                response.trim().equals("[]") || response.contains("[]"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita", e);
        }
    }
    
    // ========================================
    // ANÁLISE DE VALOR LIMITE - Nome da Cidade
    // ========================================
    
    // Valor Limite: Nome muito curto (1 caractere)
    @Test
    public void testValorLimite_CidadeUmCaractere() {
        try {
            String response = ViaCepService.buscarEndereco("SP", "A", "Rua");
            
            assertTrue("Deve retornar lista vazia", 
                response.trim().equals("[]") || response.contains("[]"));
            
        } catch (Exception e) {
            assertNotNull("Exceção aceita", e);
        }
    }
    
    // Valor Limite: Nome de tamanho normal (válido)
    @Test
    public void testValorLimite_CidadeTamanhoNormal() {
        try {
            String response = ViaCepService.buscarEndereco("RJ", "Rio de Janeiro", "Atlantica");
            
            assertNotNull("Response não deve ser nulo", response);
            
        } catch (Exception e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
    }
}
