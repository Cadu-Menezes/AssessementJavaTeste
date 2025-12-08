package com.assessement.ExercicioUm;

import org.junit.Test;
import static org.junit.Assert.*;

/*
    Exercício 1.1
 */
public class TestesExploratorios {

    // ========================================
    // 1. TESTES COM VALORES NORMAIS/VÁLIDOS
    // ========================================
    
    @Test
    public void testPesoSaudavel() {
        // Cenário: Pessoa com peso saudável
        double peso = 70.0;
        double altura = 1.75;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals(22.86, imc, 0.01);
        assertEquals("Saudável", classificacao);
    }
    
    @Test
    public void testSobrepeso() {
        // Cenário: Pessoa com sobrepeso
        double peso = 85.0;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals(29.41, imc, 0.01);
        assertEquals("Sobrepeso", classificacao);
    }
    
    @Test
    public void testObesidade() {
        // Cenário: Pessoa com obesidade
        double peso = 100.0;
        double altura = 1.65;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals(36.73, imc, 0.01);
        assertEquals("Obesidade Grau II", classificacao);
    }
    
    @Test
    public void testMagreza() {
        // Cenário: Pessoa com magreza
        double peso = 45.0;
        double altura = 1.60;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals(17.58, imc, 0.01);
        assertEquals("Magreza leve", classificacao);
    }
    
    // ========================================
    // 2. TESTES COM VALORES EXTREMOS
    // ========================================
    
    @Test
    public void testValoresMuitoBaixos() {
        // Cenário: Valores muito baixos
        double peso = 30.0;
        double altura = 1.50;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals(13.33, imc, 0.01);
        assertEquals("Magreza grave", classificacao);
    }
    
    @Test
    public void testValoresMuitoAltos() {
        // Cenário: Valores muito altos
        double peso = 150.0;
        double altura = 1.60;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals(58.59, imc, 0.01);
        assertEquals("Obesidade Grau III", classificacao);
    }
    
    // ========================================
    // 3. TESTES NOS LIMITES (BOUNDARIES)
    // ========================================
    
    @Test
    public void testLimiteInferiorSaudavel() {
        // Cenário: Limite inferior "Saudável" (IMC >= 18.5)
        double peso = 55.0;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals(19.03, imc, 0.01);
        assertEquals("Saudável", classificacao);
    }
    
    @Test
    public void testLimiteSuperiorSaudavel() {
        // Cenário: Limite superior "Saudável" (IMC < 25.0)
        double peso = 72.0;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals(24.91, imc, 0.01);
        assertEquals("Saudável", classificacao);
    }
    
    @Test
    public void testExatamenteNoLimite25() {
        // Cenário: Exatamente no limite (IMC = 25.0)
        double peso = 72.25;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals(25.0, imc, 0.01);
        assertEquals("Sobrepeso", classificacao);
    }
    
    // ========================================
    // 4. TESTES COM VALORES PROBLEMÁTICOS
    // ========================================
    
    @Test
    public void testPesoNegativo() {
        // Cenário: Peso negativo (valor inválido)
        double peso = -70.0;
        double altura = 1.75;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        
        // Sistema não valida - retorna valor negativo
        assertTrue("IMC deveria ser negativo ou sistema deveria validar", imc < 0);
    }
    
    @Test
    public void testPesoZero() {
        // Cenário: Peso zero
        double peso = 0.0;
        double altura = 1.75;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        
        assertEquals(0.0, imc, 0.01);
    }
    
    @Test
    public void testAlturaZero() {
        // Cenário: Altura zero (divisão por zero)
        double peso = 70.0;
        double altura = 0.0;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        
        // Verifica se retorna Infinity (divisão por zero em Java)
        assertTrue("Divisão por zero deveria retornar Infinity", Double.isInfinite(imc));
    }
    
    @Test
    public void testAlturaIrrealista() {
        // Cenário: Altura muito pequena (irrealista)
        double peso = 70.0;
        double altura = 0.5;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals(280.0, imc, 0.01);
        assertEquals("Obesidade Grau III", classificacao);
    }
}
