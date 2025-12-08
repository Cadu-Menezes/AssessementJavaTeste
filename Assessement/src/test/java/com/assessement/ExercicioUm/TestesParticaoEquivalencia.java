package com.assessement.ExercicioUm;

import org.junit.Test;
import static org.junit.Assert.*;

/*
    Cada teste representa uma categoria/grupo de valores similares
*/
public class TestesParticaoEquivalencia {

    @Test
    public void testeParticaoMagreza() {
        // Representa toda a categoria "Magreza leve" (17.0 <= IMC < 18.5)
        double peso = 45.0;
        double altura = 1.60;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC calculado", 17.58, imc, 0.01);
        assertEquals("Classificacao para magreza", "Magreza leve", classificacao);
    }
    
    @Test
    public void testeParticaoSaudavel() {
        // Representa toda a categoria "Saudável" (18.5 <= IMC < 25.0)
        double peso = 70.0;
        double altura = 1.75;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC calculado", 22.86, imc, 0.01);
        assertEquals("Classificacao para peso saudavel", "Saudável", classificacao);
    }
    
    @Test
    public void testeParticaoSobrepeso() {
        // Representa toda a categoria "Sobrepeso" (25.0 <= IMC < 30.0)
        double peso = 85.0;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC calculado", 29.41, imc, 0.01);
        assertEquals("Classificacao para sobrepeso", "Sobrepeso", classificacao);
    }
    
    @Test
    public void testeParticaoObesidadeGrauI() {
        // Representa toda a categoria "Obesidade Grau I" (30.0 <= IMC < 35.0)
        double peso = 90.0;
        double altura = 1.65;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC calculado", 33.06, imc, 0.01);
        assertEquals("Classificacao para obesidade I", "Obesidade Grau I", classificacao);
    }
    
    @Test
    public void testeParticaoObesidadeGrauII() {
        // Representa toda a categoria "Obesidade Grau II" (35.0 <= IMC < 40.0)
        double peso = 100.0;
        double altura = 1.65;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC calculado", 36.73, imc, 0.01);
        assertEquals("Classificacao para obesidade II", "Obesidade Grau II", classificacao);
    }
    
    @Test
    public void testeParticaoObesidadeGrauIII() {
        // Representa toda a categoria "Obesidade Grau III" (IMC >= 40.0)
        double peso = 120.0;
        double altura = 1.60;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC calculado", 46.88, imc, 0.01);
        assertEquals("Classificacao para obesidade III", "Obesidade Grau III", classificacao);
    }
    
    @Test
    public void testeParticaoMagrezaGrave() {
        // Representa toda a categoria "Magreza grave" (IMC < 16.0)
        double peso = 35.0;
        double altura = 1.60;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC calculado", 13.67, imc, 0.01);
        assertEquals("Classificacao para magreza grave", "Magreza grave", classificacao);
    }
}