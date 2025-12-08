package com.assessement.ExercicioDois;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import com.assessement.ExercicioDois.MathFunctions;
import static org.junit.Assert.*;

/*
    Testes baseados em propriedades usando jqwik para MathFunctions
    Conforme solicitado: MultiplyByTwo, GenerateMultiplicationTable, IsPrime e CalculateAverage
*/
public class TestesJqwikPropriedades {


    /*
        a) MultiplyByTwo: valida que o resultado é sempre par
    */
    @Property
    void multiplyByTwoResultIsAlwaysEven(@ForAll int number) {
        int result = MathFunctions.multiplyByTwo(number);
        assertTrue("Resultado deve ser par", result % 2 == 0);
    }

    /*
        a) MultiplyByTwo: valida casos extremos (overflow prevention)
    */
    @Property
    void multiplyByTwoExtremeValues(@ForAll @IntRange(min = Integer.MIN_VALUE / 2, max = Integer.MAX_VALUE / 2) int number) {
        int result = MathFunctions.multiplyByTwo(number);
        assertEquals("Multiplicação por 2 deve ser correta", number * 2, result);
        assertTrue("Resultado deve ser par", result % 2 == 0);
    }

    /*
        b) GenerateMultiplicationTable: valida que todos os elementos são múltiplos do número original
    */
    @Property
    void allElementsAreMultiplesOfOriginalNumber(
            @ForAll @IntRange(min = 1, max = 100) int number,
            @ForAll @IntRange(min = 1, max = 20) int limit) {
        
        int[] table = MathFunctions.generateMultiplicationTable(number, limit);
        
        assertEquals("Tamanho da tabela deve ser igual ao limite", limit, table.length);
        
        for (int i = 0; i < table.length; i++) {
            int expectedValue = number * (i + 1);
            assertEquals("Elemento deve ser múltiplo correto", expectedValue, table[i]);
            assertEquals("Deve ser múltiplo do número original", 0, table[i] % number);
        }
    }

    /*
        b) GenerateMultiplicationTable: casos extremos incluindo zero
    */
    @Property
    void multiplicationTableWithZero(@ForAll @IntRange(min = 1, max = 10) int limit) {
        int[] table = MathFunctions.generateMultiplicationTable(0, limit);
        
        for (int value : table) {
            assertEquals("Multiplicação por zero deve resultar em zero", 0, value);
        }
    }

    /*
        c) IsPrime: valida que para qualquer número primo, não há divisores além de 1 e ele mesmo
    */
    @Property
    void primeNumbersHaveOnlyTwoDivisors(@ForAll("primeNumbers") int primeNumber) {
        assertTrue("Número deve ser identificado como primo", MathFunctions.isPrime(primeNumber));
        
        // Verifica que não há divisores além de 1 e o próprio número
        int divisorCount = 0;
        for (int i = 1; i <= primeNumber; i++) {
            if (primeNumber % i == 0) {
                divisorCount++;
            }
        }
        
        assertEquals("Números primos devem ter exatamente 2 divisores", 2, divisorCount);
    }

    /*
        c) IsPrime: valida casos não-primos usando gerador personalizado
    */
    @Property
    void nonPrimeNumbersAreCorrectlyIdentified(@ForAll("compositeNumbers") int compositeNumber) {
        assertFalse("Número composto deve ser identificado como não-primo", 
                    MathFunctions.isPrime(compositeNumber));
    }

    /*
        c) IsPrime: casos especiais (números <= 1)
    */
    @Property
    void numbersLessThanOrEqualToOneAreNotPrime(@ForAll @IntRange(max = 1) int number) {
        assertFalse("Números <= 1 não devem ser primos", MathFunctions.isPrime(number));
    }

    /*
        d) CalculateAverage: verifica se o resultado está sempre entre o menor e o maior valor do array
    */
    @Property
    void averageIsBetweenMinAndMax(@ForAll("nonEmptyIntArrays") int[] numbers) {
        double average = MathFunctions.calculateAverage(numbers);
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int number : numbers) {
            min = Math.min(min, number);
            max = Math.max(max, number);
        }
        
        assertTrue("Média deve ser >= valor mínimo", average >= min);
        assertTrue("Média deve ser <= valor máximo", average <= max);
    }

    /*
        d) CalculateAverage: valida cálculo correto da média
    */
    @Property
    void averageCalculationIsCorrect(@ForAll("nonEmptyIntArrays") int[] numbers) {
        double average = MathFunctions.calculateAverage(numbers);
        
        long sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        double expectedAverage = (double) sum / numbers.length;
        
        assertEquals("Média deve ser calculada corretamente", expectedAverage, average, 0.0001);
    }

    /*
        d) CalculateAverage: casos extremos - array com um elemento
    */
    @Property
    void averageOfSingleElementArrayIsTheElement(@ForAll int singleValue) {
        int[] array = {singleValue};
        double average = MathFunctions.calculateAverage(array);
        
        assertEquals("Média de um elemento deve ser o próprio elemento", 
                     (double) singleValue, average, 0.0001);
    }

    /*
        d) CalculateAverage: casos de falha - testa exceções para casos inválidos
    */
    @Property
    void calculateAverageThrowsExceptionForInvalidInput() {
        try {
            MathFunctions.calculateAverage(null);
            fail("Deve lançar IllegalArgumentException para array null");
        } catch (IllegalArgumentException e) {
            // Comportamento esperado
        }
        
        try {
            MathFunctions.calculateAverage(new int[0]);
            fail("Deve lançar IllegalArgumentException para array vazio");
        } catch (IllegalArgumentException e) {
            // Comportamento esperado
        }
    }

    // =====================================
    // GERADORES DE DADOS PERSONALIZADOS
    // =====================================

    /*
        Gerador personalizado para números primos
    */
    @Provide
    Arbitrary<Integer> primeNumbers() {
        return Arbitraries.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
    }

    /*
        Gerador personalizado para números compostos (não-primos)
    */
    @Provide
    Arbitrary<Integer> compositeNumbers() {
        return Arbitraries.of(4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 36);
    }

    /*
        Gerador personalizado para arrays não-vazios de inteiros
        Usado para testes realistas de média
    */
    @Provide
    Arbitrary<int[]> nonEmptyIntArrays() {
        return Arbitraries.integers().between(-1000, 1000)
                         .array(int[].class)
                         .ofMinSize(1)
                         .ofMaxSize(20);
    }
}