package com.assessement.ExercicioUm;

import org.junit.Test;
import static org.junit.Assert.*;

/*  
    Testa valores exatamente nos limites entre as categorias
*/
public class TestesValorLimite {

    @Test
    public void testeLimiteExato16() {
        // Limite entre Magreza grave e Magreza moderada (IMC = 16.0)
        double peso = 46.24;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC no limite 16.0", 16.00, imc, 0.01);
        assertEquals("Deve ser Magreza moderada", "Magreza moderada", classificacao);
    }
    
    @Test
    public void testeLimiteExato17() {
        // Limite entre Magreza moderada e Magreza leve (IMC = 17.0)
        double peso = 49.13;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC no limite 17.0", 17.00, imc, 0.01);
        assertEquals("Deve ser Magreza leve", "Magreza leve", classificacao);
    }
    
    @Test
    public void testeLimiteExato18_5() {
        // Limite entre Magreza leve e Saudável (IMC = 18.5)
        double peso = 53.46;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC no limite 18.5", 18.50, imc, 0.01);
        assertEquals("Deve ser Saudavel", "Saudável", classificacao);
    }
    
    @Test
    public void testeLimiteExato25() {
        // Limite entre Saudável e Sobrepeso (IMC = 25.0)
        double peso = 72.25;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC no limite 25.0", 25.00, imc, 0.01);
        assertEquals("Deve ser Sobrepeso", "Sobrepeso", classificacao);
    }
    
    @Test
    public void testeLimiteExato30() {
        // Limite entre Sobrepeso e Obesidade Grau I (IMC = 30.0)
        double peso = 86.70;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC no limite 30.0", 30.00, imc, 0.01);
        assertEquals("Deve ser Obesidade Grau I", "Obesidade Grau I", classificacao);
    }
    
    @Test
    public void testeLimiteExato35() {
        // Limite entre Obesidade Grau I e Grau II (IMC = 35.0)
        double peso = 101.15;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC no limite 35.0", 35.00, imc, 0.01);
        assertEquals("Deve ser Obesidade Grau II", "Obesidade Grau II", classificacao);
    }
    
    @Test
    public void testeLimiteExato40() {
        // Limite entre Obesidade Grau II e Grau III (IMC = 40.0)
        double peso = 115.60;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC no limite 40.0", 40.00, imc, 0.01);
        assertEquals("Deve ser Obesidade Grau III", "Obesidade Grau III", classificacao);
    }
    
    @Test
    public void testeAcimaLimite25() {
        // Logo acima do limite (IMC = 25.01)
        double peso = 72.26;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC acima do limite 25.0", 25.00, imc, 0.1);
        assertEquals("Deve ser Sobrepeso", "Sobrepeso", classificacao);
    }
    
    @Test
    public void testeAbaixoLimite25() {
        // Logo abaixo do limite (IMC = 24.99)
        double peso = 72.22;
        double altura = 1.70;
        
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        assertEquals("IMC abaixo do limite 25.0", 24.99, imc, 0.01);
        assertEquals("Deve ser Saudavel", "Saudável", classificacao);
    }
}