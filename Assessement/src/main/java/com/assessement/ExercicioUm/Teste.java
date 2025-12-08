package com.assessement.ExercicioUm;

public class Teste {
    public static void main(String[] args) {
        System.out.println("=== TESTE DE COMPORTAMENTO - REGISTRO DE FALHAS ===\n");
        
        System.out.println("1. TESTE COM VALORES EXATOS NOS LIMITES:");
        testarLimites();
        
        System.out.println("\n2. TESTE COM VALORES PROBLEMATICOS:");
        testarValoresProblematicos();
        
        System.out.println("\n3. TESTE COM VALORES EXTREMOS:");
        testarValoresExtremos();
    }
    
    static void testarLimites() {
        // Teste do problema com == em doubles
        double peso = 72.25;
        double altura = 1.70;
        double imc = CalculoIMC.calcularPeso(peso, altura); // 25.0 exato
        String classificacao = CalculoIMC.classificarIMC(imc);
        
        System.out.printf("IMC = %.10f (deveria ser exatamente 25.0)\n", imc);
        System.out.printf("Classificacao: %s\n", classificacao);
        System.out.printf("PROBLEMA: IMC = 25.0 pode nao ser classificado corretamente devido ao uso de ==\n");
    }
    
    static void testarValoresProblematicos() {
        System.out.println("a) Altura zero (divisao por zero):");
        double imc1 = CalculoIMC.calcularPeso(70.0, 0.0);
        System.out.printf("   IMC com altura = 0: %s\n", imc1);
        System.out.printf("   Classificacao: %s\n", CalculoIMC.classificarIMC(imc1));
        
        System.out.println("\nb) Peso negativo:");
        double imc2 = CalculoIMC.calcularPeso(-70.0, 1.75);
        System.out.printf("   IMC com peso negativo: %.2f\n", imc2);
        System.out.printf("   Classificacao: %s\n", CalculoIMC.classificarIMC(imc2));
        
        System.out.println("\nc) Altura negativa:");
        double imc3 = CalculoIMC.calcularPeso(70.0, -1.75);
        System.out.printf("   IMC com altura negativa: %.2f\n", imc3);
        System.out.printf("   Classificacao: %s\n", CalculoIMC.classificarIMC(imc3));
    }
    
    static void testarValoresExtremos() {
        System.out.println("a) Valores muito pequenos:");
        double imc1 = CalculoIMC.calcularPeso(0.1, 0.1);
        System.out.printf("   IMC: %.2f, Classificacao: %s\n", imc1, CalculoIMC.classificarIMC(imc1));
        
        System.out.println("\nb) Valores muito grandes:");
        double imc2 = CalculoIMC.calcularPeso(1000.0, 3.0);
        System.out.printf("   IMC: %.2f, Classificacao: %s\n", imc2, CalculoIMC.classificarIMC(imc2));
    }
}