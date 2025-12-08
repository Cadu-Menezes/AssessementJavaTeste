package com.assessement.ExercicioUm;

/*
    Executor dos Testes Formais
    Executa todos os testes e gera relatório dos resultados
*/
public class ExecutorTestesFormais {
    
    private int testesExecutados = 0;
    private int testesAprovados = 0;
    private int testesFalharam = 0;
    
    public static void main(String[] args) {
        ExecutorTestesFormais executor = new ExecutorTestesFormais();
        executor.executarTodosOsTestes();
    }
    
    public void executarTodosOsTestes() {
        System.out.println("=====================================");
        System.out.println("  EXECUÇÃO DOS TESTES FORMAIS");
        System.out.println("=====================================");
        
        // Executa testes de partição
        executarTestesParticao();
        
        // Executa testes de limite
        executarTestesLimite();
        
        // Executa testes de valores problemáticos
        executarTestesValoresProblematicos();
        
        // Gera relatório final
        gerarRelatorioFinal();
    }
    
    private void executarTestesParticao() {
        System.out.println("\n--- TESTES DE PARTIÇÃO DE EQUIVALÊNCIA ---");
        
        // Teste 1: Magreza
        testarEDocumentar("Partição Magreza", 45.0, 1.60, "Magreza leve", 17.58);
        
        // Teste 2: Saudável
        testarEDocumentar("Partição Saudável", 70.0, 1.75, "Saudável", 22.86);
        
        // Teste 3: Sobrepeso
        testarEDocumentar("Partição Sobrepeso", 85.0, 1.70, "Sobrepeso", 29.41);
        
        // Teste 4: Obesidade I
        testarEDocumentar("Partição Obesidade I", 90.0, 1.65, "Obesidade Grau I", 33.06);
        
        // Teste 5: Obesidade II
        testarEDocumentar("Partição Obesidade II", 100.0, 1.65, "Obesidade Grau II", 36.73);
    }
    
    private void executarTestesLimite() {
        System.out.println("\n--- TESTES DE VALOR LIMITE ---");
        
        // Testes nos limites exatos (estes provavelmente vão falhar)
        testarEDocumentar("Limite IMC = 25.0", 72.25, 1.70, "Sobrepeso", 25.00);
        testarEDocumentar("Limite IMC = 30.0", 86.70, 1.70, "Obesidade Grau I", 30.00);
        testarEDocumentar("Limite IMC = 16.0", 46.24, 1.70, "Magreza moderada", 16.00);
        testarEDocumentar("Limite IMC = 18.5", 53.46, 1.70, "Saudável", 18.50);
        
        // Testes logo acima/abaixo dos limites
        testarEDocumentar("Abaixo limite 25.0", 72.22, 1.70, "Saudável", 24.99);
    }
    
    private void executarTestesValoresProblematicos() {
        System.out.println("\n--- TESTES DE VALORES PROBLEMÁTICOS ---");
        
        // Altura zero (divisão por zero)
        System.out.println("Teste: Altura zero");
        double imcZero = CalculoIMC.calcularPeso(70.0, 0.0);
        String classZero = CalculoIMC.classificarIMC(imcZero);
        System.out.printf("  Resultado: IMC=%.2f, Classificação=%s\n", imcZero, classZero);
        System.out.println("  Status: ❌ FALHOU - Deveria dar erro, não Infinity");
        testesFalharam++;
        testesExecutados++;
        
        // Peso negativo
        System.out.println("Teste: Peso negativo");
        double imcNeg = CalculoIMC.calcularPeso(-70.0, 1.75);
        String classNeg = CalculoIMC.classificarIMC(imcNeg);
        System.out.printf("  Resultado: IMC=%.2f, Classificação=%s\n", imcNeg, classNeg);
        System.out.println("  Status: ❌ FALHOU - Deveria dar erro, não aceitar valor negativo");
        testesFalharam++;
        testesExecutados++;
    }
    
    private void testarEDocumentar(String nomeTeste, double peso, double altura, 
                                  String classificacaoEsperada, double imcEsperado) {
        System.out.println("Teste: " + nomeTeste);
        
        // Executa o teste
        double imcCalculado = CalculoIMC.calcularPeso(peso, altura);
        String classificacaoCalculada = CalculoIMC.classificarIMC(imcCalculado);
        
        // Verifica se passou
        boolean imcCorreto = Math.abs(imcCalculado - imcEsperado) < 0.1;
        boolean classificacaoCorreta = classificacaoEsperada.equals(classificacaoCalculada);
        
        // Documenta resultado
        System.out.printf("  Peso: %.1fkg, Altura: %.2fm\n", peso, altura);
        System.out.printf("  IMC Esperado: %.2f | IMC Calculado: %.2f\n", imcEsperado, imcCalculado);
        System.out.printf("  Classificação Esperada: %s | Calculada: %s\n", 
                         classificacaoEsperada, classificacaoCalculada);
        
        if (imcCorreto && classificacaoCorreta) {
            System.out.println("  Status: ✅ PASSOU");
            testesAprovados++;
        } else {
            System.out.println("  Status: ❌ FALHOU");
            if (!imcCorreto) {
                System.out.println("    - IMC incorreto");
            }
            if (!classificacaoCorreta) {
                System.out.println("    - Classificação incorreta (provavelmente problema com ==)");
            }
            testesFalharam++;
        }
        
        testesExecutados++;
        System.out.println();
    }
    
    private void gerarRelatorioFinal() {
        System.out.println("=====================================");
        System.out.println("        RELATÓRIO FINAL");
        System.out.println("=====================================");
        System.out.printf("Total de testes executados: %d\n", testesExecutados);
        System.out.printf("Testes aprovados: %d (%.1f%%)\n", 
                         testesAprovados, (testesAprovados * 100.0 / testesExecutados));
        System.out.printf("Testes que falharam: %d (%.1f%%)\n", 
                         testesFalharam, (testesFalharam * 100.0 / testesExecutados));
        
        System.out.println("\n--- PRINCIPAIS PROBLEMAS IDENTIFICADOS ---");
        System.out.println("1. Uso inadequado de == para comparar doubles");
        System.out.println("2. Falta de validação para altura zero");
        System.out.println("3. Falta de validação para valores negativos");
        System.out.println("4. Sistema aceita valores irreais sem validação");
        
        if (testesFalharam > 0) {
            System.out.println("\nCONCLUSÃO: O sistema apresenta falhas!");
        } else {
            System.out.println("\nCONCLUSÃO: Todos os testes passaram!");
        }
    }
}