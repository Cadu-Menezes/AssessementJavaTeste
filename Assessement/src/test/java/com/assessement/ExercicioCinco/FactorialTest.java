package com.assessement.ExercicioCinco;

import org.junit.Test;
import static org.junit.Assert.*;

/*
    Testes unitários para a classe Factorial
    Cobrindo todas as decisões, ramificações e condições de contorno
*/
public class FactorialTest {

    // ========== TESTES DE CASOS VÁLIDOS ==========
    
    @Test
    public void testFactorialZero() {
        // Caso especial: 0! = 1
        assertEquals(1, Factorial.factorial(0));
    }
    
    @Test
    public void testFactorialUm() {
        // Caso base: 1! = 1
        assertEquals(1, Factorial.factorial(1));
    }
    
    @Test
    public void testFactorialDois() {
        // 2! = 2
        assertEquals(2, Factorial.factorial(2));
    }
    
    @Test
    public void testFactorialTres() {
        // 3! = 6
        assertEquals(6, Factorial.factorial(3));
    }
    
    @Test
    public void testFactorialQuatro() {
        // 4! = 24
        assertEquals(24, Factorial.factorial(4));
    }
    
    @Test
    public void testFactorialCinco() {
        // 5! = 120
        assertEquals(120, Factorial.factorial(5));
    }
    
    @Test
    public void testFactorialDez() {
        // 10! = 3,628,800
        assertEquals(3628800, Factorial.factorial(10));
    }
    
    @Test
    public void testFactorialVinte() {
        // 20! = 2,432,902,008,176,640,000
        assertEquals(2432902008176640000L, Factorial.factorial(20));
    }
    
    // ========== TESTES DE CASOS INVÁLIDOS (EXCEÇÕES) ==========
    
    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegativo() {
        // Número negativo deve lançar IllegalArgumentException
        Factorial.factorial(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegativoMenosUm() {
        // -1 deve lançar exceção
        Factorial.factorial(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegativoGrande() {
        // Número negativo grande deve lançar exceção
        Factorial.factorial(-100);
    }
    
    @Test
    public void testFactorialNegativoMensagemErro() {
        // Verifica se a mensagem de erro está correta
        try {
            Factorial.factorial(-5);
            fail("Deveria lançar IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Input number cannot be negative", e.getMessage());
        }
    }
    
    // ========== TESTES DE VALORES LIMITES (BOUNDARY) ==========
    
    @Test
    public void testFactorialLimiteInferior() {
        // Limite inferior válido: 0
        assertEquals(1, Factorial.factorial(0));
    }
    
    @Test
    public void testFactorialLimiteSuperiorSeguro() {
        // Maior valor que não causa overflow em long: 20!
        // 21! = 51,090,942,171,709,440,000 já causaria overflow
        assertEquals(2432902008176640000L, Factorial.factorial(20));
    }
    
    // ========== TESTES DE COBERTURA DO LOOP ==========
    
    @Test
    public void testFactorialLoopNaoExecuta() {
        // n = 0: loop não executa (i <= 0 é falso)
        assertEquals(1, Factorial.factorial(0));
    }
    
    @Test
    public void testFactorialLoopUmaIteracao() {
        // n = 1: loop executa exatamente 1 vez
        assertEquals(1, Factorial.factorial(1));
    }
    
    @Test
    public void testFactorialLoopMultiplasIteracoes() {
        // n = 5: loop executa 5 vezes
        assertEquals(120, Factorial.factorial(5));
    }
    
    // ========== TESTES DE EQUIVALÊNCIA ==========
    
    @Test
    public void testFactorialValoresEquivalentesPositivos() {
        // Classe de equivalência: números pequenos (1-5)
        assertEquals(1, Factorial.factorial(1));
        assertEquals(2, Factorial.factorial(2));
        assertEquals(6, Factorial.factorial(3));
        assertEquals(24, Factorial.factorial(4));
        assertEquals(120, Factorial.factorial(5));
    }
    
    @Test
    public void testFactorialValoresEquivalentesMedianos() {
        // Classe de equivalência: números médios (6-15)
        assertEquals(720, Factorial.factorial(6));
        assertEquals(40320, Factorial.factorial(8));
        assertEquals(1307674368000L, Factorial.factorial(15));
    }
    
    // ========== TESTE DE OVERFLOW (ANÁLISE DE ROBUSTEZ) ==========
    
    @Test
    public void testFactorialOverflow() {
        // 21! causa overflow em long (retorna valor incorreto)
        // Este teste DOCUMENTA o comportamento atual (não lança exceção)
        long resultado = Factorial.factorial(21);
        // Valor incorreto devido a overflow
        assertTrue(resultado < 0); // Overflow faz resultado ficar negativo
    }
}
