package com.assessement.ExercicioDois;

/*
    Versão refatorada da classe MathFunctions com injeção de dependência
*/
public class MathFunctionsRefatorado {
    
    private final MathLogger logger;
    
    /**
        Construtor que aceita dependência injetada
        @param logger interface para logging de operações matemáticas
    */
    public MathFunctionsRefatorado(MathLogger logger) {
        this.logger = logger;
    }
    
    public int multiplyByTwo(int number) {
        int[] inputs = {number};
        logger.log("multiplyByTwo", inputs);
        return number * 2;
    }
    
    public int[] generateMultiplicationTable(int number, int limit) {
        int[] inputs = {number, limit};
        logger.log("generateMultiplicationTable", inputs);
        
        int[] result = new int[limit];
        for (int i = 0; i < limit; i++) {
            result[i] = number * (i + 1);
        }
        return result;
    }

    public boolean isPrime(int number) {
        int[] inputs = {number};
        logger.log("isPrime", inputs);
        
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
    
    public double calculateAverage(int[] numbers) {
        logger.log("calculateAverage", numbers != null ? numbers : new int[0]);
        
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        
        double sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }
}