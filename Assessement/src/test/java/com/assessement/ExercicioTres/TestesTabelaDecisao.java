package com.assessement.ExercicioTres;

import org.junit.Test;
import static org.junit.Assert.*;

/*
    Exercício 3.3 - Testes baseados em Tabela de Decisão
    
    Tabela de Decisão para busca por endereço:
    -----------------------------------------------------------------------------------------------
    |  L | UF Válida | Cidade Válida | Acentuação | Logradouro Existe | Resultado Esperado        |
    |----|-----------|---------------|------------|-------------------|---------------------------|
    |  1 |    Sim    |      Sim      |    Sim     |        Sim        | Lista com endereços       |
    -----------------------------------------------------------------------------------------------
    |  2 |    Sim    |      Sim      |    Não     |        Sim        | Lista com endereços       |
    -----------------------------------------------------------------------------------------------
    |  3 |    Sim    |      Sim      |    Sim     |        Não        | Lista vazia []            |
    -----------------------------------------------------------------------------------------------
    |  4 |    Sim    |      Não      |    -       |        -          | Lista vazia []            |
    -----------------------------------------------------------------------------------------------
    |  5 |    Não    |      -        |    -       |        -          | Lista vazia [] ou erro    |
    -----------------------------------------------------------------------------------------------
*/
public class TestesTabelaDecisao {

    // Caso 1: UF válida + Cidade válida + Com acentuação + Logradouro existe
    @Test
    public void testCaso1_TudoValidoComAcentuacao() {
        try {
            String response = ViaCepService.buscarEndereco("SP", "São Paulo", "Avenida Paulista");
            
            assertNotNull("Response não deve ser nulo", response);
            assertFalse("Deve retornar resultados", response.contains("[]"));
            assertTrue("Deve conter dados do logradouro", 
                response.toLowerCase().contains("paulista"));
            
        } catch (Exception e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
    }
    
    // Caso 2: UF válida + Cidade válida + Sem acentuação + Logradouro existe
    @Test
    public void testCaso2_TudoValidoSemAcentuacao() {
        try {
            String response = ViaCepService.buscarEndereco("SP", "Sao Paulo", "Paulista");
            
            assertNotNull("Response não deve ser nulo", response);
            assertFalse("Deve retornar resultados", response.contains("[]"));
            assertTrue("Deve conter dados", response.length() > 10);
            
        } catch (Exception e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
    }
    
    // Caso 3: UF válida + Cidade válida + Com acentuação + Logradouro NÃO existe
    @Test
    public void testCaso3_LogradouroInexistente() {
        try {
            String response = ViaCepService.buscarEndereco("RJ", "Rio de Janeiro", "Rua Inexistente XYZ 999");
            
            assertNotNull("Response não deve ser nulo", response);
            assertTrue("Deve retornar lista vazia", 
                response.trim().equals("[]") || response.contains("[]"));
            
        } catch (Exception e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
    }
    
    // Caso 4: UF válida + Cidade NÃO válida
    @Test
    public void testCaso4_CidadeInvalida() {
        try {
            String response = ViaCepService.buscarEndereco("SP", "CidadeQueNaoExiste123", "Rua Qualquer");
            
            assertNotNull("Response não deve ser nulo", response);
            assertTrue("Deve retornar lista vazia para cidade inválida", 
                response.trim().equals("[]") || response.contains("[]"));
            
        } catch (Exception e) {
            // Aceita exceção também
            assertNotNull("Exceção aceita para cidade inválida", e);
        }
    }
    
    // Caso 5: UF NÃO válida
    @Test
    public void testCaso5_UfInvalida() {
        try {
            String response = ViaCepService.buscarEndereco("ZZ", "Qualquer", "Qualquer");
            
            assertNotNull("Response não deve ser nulo", response);
            assertTrue("Deve retornar lista vazia ou erro para UF inválida", 
                response.trim().equals("[]") || response.contains("[]") || response.contains("erro"));
            
        } catch (Exception e) {
            // Aceita exceção também para UF inválida
            assertNotNull("Exceção aceita para UF inválida", e);
        }
    }
    
    // Caso adicional: Cidade com caracteres especiais e acentos
    @Test
    public void testCasoExtra_CidadeComCaracteresEspeciais() {
        try {
            String response = ViaCepService.buscarEndereco("MG", "Belo Horizonte", "Praça da Liberdade");
            
            assertNotNull("Response não deve ser nulo", response);
            assertFalse("Deve retornar resultados para cidade e logradouro válidos", 
                response.trim().equals("[]"));
            
        } catch (Exception e) {
            fail("Não deveria lançar exceção para entrada válida: " + e.getMessage());
        }
    }
}
